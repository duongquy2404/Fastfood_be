package com.example.fastfood.service;

import com.example.fastfood.model.dto.UserRegisterDTO;
import com.example.fastfood.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(UserRegisterDTO userDTO);
    User login(String username);

    boolean checkUserByUsername(String username);

    Optional<User> getUserById(Integer id);

    //update
    User saveUser(User user);

    Optional<User> findById(Long id);

    List<User> findAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    // Phương thức tùy ý để tìm kiếm người dùng theo tiêu chí cụ thể
    List<User> findUsersByCriteria(String criteria);

    // Các phương thức khác như đổi mật khẩu, phục hồi mật khẩu, v.v.
    void changePassword(Long userId, String newPassword);

    // Phương thức để xử lý đăng nhập của người dùng
    User login(String username, String password);

    // Phương thức để xác thực người dùng hoặc kiểm tra quyền
    boolean checkIfUserHasRole(Long userId, String role);
}
