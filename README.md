# ĐẶC TẢ PHẦN MỀM
## HỆ THỐNG QUẢN LÝ GIỎ HÀNG – SHOPPING CART
**Áp dụng mẫu thiết kế Humble Object**

### 1. Giới thiệu đề tài

**1.1. Tên đề tài**

Thiết kế và xây dựng ứng dụng quản lý giỏ hàng (Shopping Cart) sử dụng Java, áp dụng mẫu thiết kế Humble Object.

**1.2. Mục tiêu**

Xây dựng một ứng dụng cho phép người dùng quản lý sản phẩm trong giỏ hàng thông qua giao diện trực quan, đồng thời minh họa cách phân tách giao diện và logic nghiệp vụ theo mẫu thiết kế Humble Object.

Mục tiêu cụ thể:
- Xây dựng chức năng thêm sản phẩm vào giỏ hàng.
- Cho phép xóa sản phẩm khỏi giỏ hàng.
- Tự động cập nhật số lượng khi thêm sản phẩm đã tồn tại.
- Tính thành tiền của từng sản phẩm và tổng tiền giỏ hàng.
- Hiển thị dữ liệu dưới dạng bảng.
- Tách biệt giao diện, nghiệp vụ và lưu trữ dữ liệu.
- Xây dựng Unit Test để kiểm tra tính chính xác của chương trình.

### 2. Phạm vi hệ thống

**2.1. Chức năng được triển khai**

Ứng dụng hỗ trợ:
- Xem danh sách sản phẩm trong giỏ hàng.
- Chọn sản phẩm từ danh sách mẫu.
- Nhập số lượng sản phẩm cần thêm.
- Thêm sản phẩm vào giỏ.
- Xóa sản phẩm được chọn.
- Hiển thị đơn giá, số lượng, thành tiền và tổng tiền.
- Kiểm tra dữ liệu đầu vào và thông báo lỗi.

**2.2. Giới hạn hệ thống**

Phiên bản hiện tại là ứng dụng Java chạy trên máy tính, sử dụng Java Swing.

- Dữ liệu được lưu trong bộ nhớ bằng `InMemoryCartRepository`.
- Danh sách sản phẩm được định nghĩa sẵn.
- Chưa tích hợp cơ sở dữ liệu PostgreSQL.
- Chưa hỗ trợ đăng nhập, thanh toán và đặt hàng.
- Chưa xây dựng REST API.
- Dữ liệu giỏ hàng không được giữ lại sau khi tắt ứng dụng.

### 3. Đặc tả yêu cầu chức năng

| Mã | Chức năng | Mô tả |
|---|---|---|
| FR01 | Xem giỏ hàng | Hiển thị danh sách sản phẩm đang có trong giỏ |
| FR02 | Thêm sản phẩm | Người dùng chọn sản phẩm và nhập số lượng cần thêm |
| FR03 | Cập nhật số lượng | Khi thêm sản phẩm đã tồn tại, hệ thống cộng dồn số lượng |
| FR04 | Xóa sản phẩm | Người dùng chọn một dòng trong bảng và xóa sản phẩm |
| FR05 | Tính thành tiền | Tính đơn giá nhân với số lượng của từng sản phẩm |
| FR06 | Tính tổng tiền | Tính tổng thành tiền của tất cả sản phẩm |
| FR07 | Kiểm tra dữ liệu | Từ chối số lượng không hợp lệ và sản phẩm không khả dụng |
| FR08 | Hiển thị kết quả | Cập nhật bảng và tổng tiền sau mỗi thao tác thành công |
### 4. Đặc tả nghiệp vụ (Business Rules)

**BR01 – Số lượng hợp lệ:** Số lượng thêm vào giỏ phải lớn hơn 0.

**BR02 – Sản phẩm hợp lệ:** Sản phẩm phải tồn tại trong danh sách và có trạng thái khả dụng.

**BR03 – Không trùng dòng sản phẩm:** Nếu cùng một sản phẩm được thêm nhiều lần với cùng đơn giá, hệ thống cộng dồn số lượng thay vì tạo dòng mới.

**BR04 – Tính thành tiền:**

`Thành tiền = Đơn giá × Số lượng`

**BR05 – Tính tổng giỏ hàng:**

`Tổng tiền = Tổng thành tiền của tất cả sản phẩm`

**BR06 – Xóa sản phẩm:** Chỉ cho phép xóa sản phẩm đang tồn tại trong giỏ hàng.

**BR07 – Kiểm tra giá:** Nếu đơn giá đã xác minh của sản phẩm khác giá sản phẩm đang lưu trong giỏ, hệ thống báo lỗi thay vì tự động thay đổi giá.

**BR08 – Giỏ hàng không tồn tại:** Khi yêu cầu lấy hoặc xóa sản phẩm từ một giỏ hàng không tồn tại, hệ thống thông báo lỗi. Khi thêm sản phẩm hợp lệ vào một mã giỏ hàng mới, hệ thống có thể tạo giỏ hàng.

### 5. Đặc tả các ca sử dụng (Use Cases)

**UC01 – Thêm sản phẩm vào giỏ hàng**

- Tác nhân: Người dùng.
- Điều kiện trước: Ứng dụng đang hoạt động, sản phẩm có trong danh sách lựa chọn.
- Luồng chính:
  1. Người dùng chọn sản phẩm.
  2. Người dùng nhập số lượng.
  3. Nhấn nút “Thêm vào giỏ”.
  4. Hệ thống kiểm tra thông tin sản phẩm.
  5. Hệ thống thêm sản phẩm hoặc cộng dồn số lượng.
  6. Hệ thống cập nhật bảng và tổng tiền.
- Kết quả: Giỏ hàng được cập nhật thành công.
- Ngoại lệ: Số lượng không hợp lệ hoặc sản phẩm không khả dụng.

**UC02 – Xóa sản phẩm khỏi giỏ hàng**

- Tác nhân: Người dùng.
- Điều kiện trước: Giỏ hàng có sản phẩm.
- Luồng chính:
  1. Người dùng chọn sản phẩm trong bảng.
  2. Nhấn nút “Xóa sản phẩm được chọn”.
  3. Hệ thống xác định mã sản phẩm.
  4. Xóa sản phẩm khỏi giỏ.
  5. Cập nhật bảng và tổng tiền.
- Kết quả: Sản phẩm được xóa thành công.
- Ngoại lệ: Người dùng chưa chọn sản phẩm hoặc sản phẩm không tồn tại.

**UC03 – Xem và tính tổng tiền giỏ hàng**

- Tác nhân: Người dùng.
- Điều kiện trước: Giỏ hàng đã được khởi tạo.
- Luồng chính:
  1. Hệ thống lấy dữ liệu giỏ hàng.
  2. Tính thành tiền của từng sản phẩm.
  3. Tính tổng tiền.
  4. Hiển thị kết quả lên JTable.
- Kết quả: Người dùng xem được nội dung và tổng tiền hiện tại.

### 6. Kiến trúc phần mềm – Humble Object

Hệ thống được thiết kế theo nguyên tắc phân tách giao diện, xử lý nghiệp vụ và lưu trữ dữ liệu.
**6.1. CartFrame – Presentation Layer**

- Xây dựng giao diện bằng Java Swing.
- Hiển thị dữ liệu bằng JTable.
- Nhận thao tác từ người dùng.
- Gọi CartController.
- Không trực tiếp tính tổng tiền hoặc chỉnh sửa dữ liệu giỏ hàng.

**6.2. CartController – Humble Controller**

- Tiếp nhận CartRequest.
- Gọi các phương thức của CartService.
- Chuyển dữ liệu nghiệp vụ thành CartResponse.
- Không trực tiếp chứa các quy tắc nghiệp vụ của giỏ hàng.

**6.3. CartService – Application Layer**

- Điều phối các chức năng thêm, xóa và lấy giỏ hàng.
- Kiểm tra thông tin đầu vào.
- Xác minh sản phẩm thông qua ProductPriceVerifier.
- Sử dụng CartRepository để truy xuất và lưu dữ liệu.

**6.4. Cart và CartItem – Domain Layer**

- Cart quản lý danh sách sản phẩm.
- CartItem lưu thông tin sản phẩm, giá và số lượng.
- Cart thực hiện cộng dồn số lượng và tính tổng tiền.
- CartItem thực hiện tính thành tiền.

**6.5. Infrastructure Layer**

- CartRepository là interface định nghĩa thao tác lưu trữ.
- InMemoryCartRepository triển khai lưu dữ liệu trong bộ nhớ.
- ProductPriceVerifier là interface xác minh sản phẩm.
- StaticProductPriceVerifier cung cấp thông tin sản phẩm mẫu.

**Ý nghĩa áp dụng Humble Object:**

Các thành phần giao tiếp với người dùng được giữ đơn giản, trong khi logic nghiệp vụ nằm ở các lớp có thể kiểm thử độc lập. Cách thiết kế này giúp giảm sự phụ thuộc vào Java Swing, nâng cao khả năng bảo trì, mở rộng và kiểm thử phần mềm.

### 7. Đặc tả dữ liệu

7.1. Giỏ hàng – Cart

Thuộc tính

Kiểu dữ liệu

Mô tả

id

String

Mã giỏ hàng

items

List<CartItem>

Danh sách sản phẩm

7.2. Chi tiết giỏ hàng – CartItem

Thuộc tính

Kiểu dữ liệu

Mô tả

productId

String

Mã sản phẩm

productName

String

Tên sản phẩm

price

BigDecimal

Đơn giá

quantity

int

Số lượng

7.3. Dữ liệu sản phẩm mẫu

Mã SP

Tên sản phẩm

Giá

Trạng thái

P001

Áo thun

150.000đ

Khả dụng

P002

Quần jean

350.000đ

Khả dụng

P003

Áo khoác

450.000đ

Không khả dụng

Giao diện hiện tại cho phép chọn P001 và P002.

8. Yêu cầu phi chức năng

Dễ sử dụng: Giao diện đơn giản, hiển thị dữ liệu dạng bảng.

Tính đúng đắn: Kết quả tính toán phải chính xác.

Khả năng kiểm thử: Các nghiệp vụ quan trọng được kiểm thử bằng JUnit 5.

Khả năng bảo trì: Các lớp được phân chia trách nhiệm rõ ràng.

Khả năng mở rộng: Có thể thay Repository bộ nhớ bằng Repository cơ sở dữ liệu.

Khả năng tương thích: Ứng dụng sử dụng Java 17, Maven và Java Swing.

Tính ổn định: Các thao tác không hợp lệ phải được xử lý bằng thông báo lỗi thích hợp.

9. Kế hoạch kiểm thử

Hệ thống có 9 trường hợp kiểm thử nghiệp vụ:

STT

Nội dung kiểm thử

Kết quả mong đợi

TC01

Thêm sản phẩm hợp lệ

Thêm thành công

TC02

Thêm trùng sản phẩm

Số lượng được cộng dồn

TC03

Số lượng bằng 0 hoặc âm

Báo lỗi

TC04

Sản phẩm không tồn tại

Báo lỗi

TC05

Sản phẩm không khả dụng

Báo lỗi

TC06

Xóa sản phẩm có trong giỏ

Xóa thành công

TC07

Tính tổng nhiều sản phẩm

Tổng tiền chính xác

TC08

Xem giỏ hàng không tồn tại

Báo lỗi

TC09

Xóa sản phẩm không có trong giỏ

Báo lỗi

Công cụ kiểm thử: JUnit 5, Maven Surefire Plugin.

Lệnh chạy kiểm thử: mvn clean test.

10. Công nghệ sử dụng

Công nghệ

Vai trò

Java 17

Ngôn ngữ lập trình

Java Swing

Xây dựng giao diện

JTable

Hiển thị giỏ hàng dạng bảng

Maven

Quản lý và biên dịch project

JUnit 5

Unit Testing

Git

Quản lý phiên bản

GitHub

Lưu trữ và chia sẻ mã nguồn

11. Kết luận

Đề tài Shopping Cart minh họa việc áp dụng mẫu Humble Object vào thiết kế và xây dựng phần mềm Java.

Việc tách biệt giao diện, Controller, Service, Domain và Repository giúp hệ thống có cấu trúc rõ ràng, giảm sự phụ thuộc giữa các thành phần và tạo điều kiện cho kiểm thử tự động.
Phiên bản hiện tại đáp ứng các chức năng cơ bản của giỏ hàng. Trong tương lai, hệ thống có thể được mở rộng thêm chức năng cập nhật số lượng trực tiếp, kết nối PostgreSQL, quản lý người dùng, đặt hàng và thanh toán.
