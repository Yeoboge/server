package com.yeoboge.server.service.impl;

import com.yeoboge.server.domain.dto.user.UserDetailResponse;
import com.yeoboge.server.domain.dto.user.UserUpdateRequest;
import com.yeoboge.server.domain.entity.User;
import com.yeoboge.server.domain.vo.user.UpdateUser;
import com.yeoboge.server.enums.error.AuthenticationErrorCode;
import com.yeoboge.server.handler.AppException;
import com.yeoboge.server.repository.UserRepository;
import com.yeoboge.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final S3FileUploadServiceImpl s3FileUploadService;
    @Override
    public UserDetailResponse getProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new AppException(AuthenticationErrorCode.USER_NOT_FOUND,AuthenticationErrorCode.USER_NOT_FOUND.getMessage()));
        return UserDetailResponse.of(user);
    }

    @Override
    public UpdateUser updateUser(MultipartFile file, UserUpdateRequest request, Long id) {
        User existedUser = userRepository.findById(id)
                .orElseThrow(()->new AppException(AuthenticationErrorCode.USER_NOT_FOUND,AuthenticationErrorCode.USER_NOT_FOUND.getMessage()));
        User updatedUser = new User();
        if(!file.isEmpty()) updatedUser = User.updateUserProfile(existedUser, s3FileUploadService.uploadFile(file),request.nickname());
        else if(request.isChanged())
            updatedUser = User.updateUserProfile(existedUser, null ,request.nickname());
        else updatedUser = User.updateUserProfile(existedUser, existedUser.getProfileImagePath() ,request.nickname());
        userRepository.save(updatedUser);
        return UpdateUser.builder()
                .message("프로필 변경 성공")
                .build();

    }
}
