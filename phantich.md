## Phần 1 - Phân tích logic

### 1. Sự khác nhau giữa CSRF trong Web truyền thống và REST API

| Web truyền thống (Session-Based)      | REST API (Stateless / Token-Based) |
| ------------------------------------- | ---------------------------------- |
| Dùng Session + Cookie                 | Dùng JWT/Token trong Header        |
| Trình duyệt tự gửi Cookie mỗi request | Client tự gửi token                |
| Dễ bị CSRF attack                     | Ít bị CSRF hơn                     |
| Cần CSRF Token để bảo vệ              | Thường có thể disable CSRF         |

### Vì sao Web truyền thống dễ bị CSRF?

Với ứng dụng web dùng session:

* User đăng nhập → browser lưu cookie session.
* Khi truy cập website độc hại, browser vẫn tự động gửi cookie.
* Hacker có thể giả request thay user.

Ví dụ:

```html
<form action="https://bank.com/transfer" method="POST">
    <input type="hidden" name="money" value="1000000">
</form>
```

Nếu không có CSRF token:

* Request giả vẫn hợp lệ.
* User bị thực hiện hành động ngoài ý muốn.

---

### Vì sao REST API thường disable CSRF?

REST API thường:

* Không dùng session.
* Không dùng cookie tự động.
* Dùng JWT/token trong Authorization Header.

Ví dụ:

```http
Authorization: Bearer eyJhbGciOi...
```

Browser không tự động gắn token này → khó bị CSRF attack hơn.

---

### Vì sao không được disable CSRF bừa bãi?

Nếu disable CSRF cho web session-based:

* Hacker có thể gửi request giả mạo.
* User không hề biết mình đang thao tác.
* Có nguy cơ:

    * chuyển tiền
    * đổi mật khẩu
    * xoá dữ liệu
    * mua hàng trái phép

=> Chỉ nên disable CSRF khi dùng REST API stateless/token-based.

---
