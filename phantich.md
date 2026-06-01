Với web truyền thống, người dùng đăng nhập bằng form login, server tạo session, trình duyệt tự gửi cookie session trong các request sau. Vì cookie tự động được gửi nên có nguy cơ bị tấn công CSRF: kẻ xấu lừa người dùng gửi request ngoài ý muốn khi vẫn đang đăng nhập.

Với REST API cho mobile, hệ thống thường dùng token/JWT gửi trong header:

Authorization: Bearer <token>

Client chủ động gắn token vào request, server không lưu session. Vì vậy REST API thường cấu hình:

SessionCreationPolicy.STATELESS

Do không dùng session/cookie kiểu web truyền thống, CSRF mặc định dễ gây lỗi 403 Forbidden khi gọi POST, PUT, DELETE từ Postman/mobile.

Tuy nhiên, không nên tắt CSRF mù quáng cho mọi loại ứng dụng. Nếu app vẫn dùng form login + session + cookie, việc tắt CSRF có thể khiến attacker lợi dụng phiên đăng nhập của người dùng để gửi request thay đổi dữ liệu.