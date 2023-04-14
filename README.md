# Project_Survey
# Lập trình web

# Công nghệ sử dụng

**Client:** VueJs,

**Server:** Springboot, Spring security,Spring Jpa
**Database:** postgresql

## Tính năng
### Backend: 
- Phân biệt các phiên làm việc bằng token. Sau khi người dùng đăng kí tài khoản và sau đó đăng nhập,
sẽ trả về 1 token. 
- Thực hiện các thao tác như tìm kiếm theo tên, theo ngày tạo survey gần nhất, theo câu trả lời, theo số điện thoại,
theo email của người dùng 1 cách gần đúng sử dụng truy vấn động
- Thực hiện các chức năng CRUD(create, read, update, delete). Mỗi khi muốn truy cập vào tài nguyên thì có sự can thiệp 
của Spring Security để xác thực(authentication) và phân quyền (authorization). Cơ sở để làm điều này đó là phân tích 
các claims chứa trong token trả về và lấy những thông tin tương ứng.
### Front end:
- Call api từ backend trả về, mỗi request đều kèm theo token để xác định có quyền truy cập vào url đó không
- web tĩnh là chủ yếu 
- dùng thư viện axios để call api 
### Thư mục backend chứa code spring
**config** Chứa các cấu hình cho springboot

**entity** Chứa các lơp dùng để định nghia các đối tượng tương ứng với các bảng trong postgresql

**model** Chứa các lớp dùng để trao đổi dữ liệu như DTO

**repository** Dùng định nghĩa các hàm thao tác với csdl

**rest** Định nghĩa các restAPI

**service** Định nghĩa các service

**resources** Định nghia resources

**application.properties** Chứa các thuộc tính cấu hình cho springboot

#### Thư mục frontend chưa code frontend

## Phiên bản sử dụng 
- java: version 17
- vuejs: vuejs2


