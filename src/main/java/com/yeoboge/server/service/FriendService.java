package com.yeoboge.server.service;

import com.yeoboge.server.domain.dto.PageResponse;
import com.yeoboge.server.domain.dto.friend.FriendInfoDto;
import org.springframework.data.domain.Pageable;

/**
 * 친구 관련 비즈니스 로직에 대한 메서드를 제공하는 인터페이스
 */
public interface FriendService {
    /**
     * 회원의 친구 목록을 페이징하여 조회함.
     *
     * @param id 조회할 회원 ID
     * @param pageable 페이징 관련 정보가 포함된 {@link Pageable}
     * @return 친구 DTO 리스트를 포함한 {@link PageResponse}
     */
    PageResponse getFriends(Long id, Pageable pageable);

    /**
     * 회원의 친구 요청 목록을 페이징하여 조회함.
     *
     * @param id 조회할 회원 ID
     * @param pageable 페이징 관련 정보가 포함된 {@link Pageable}
     * @return 요청을 보낸 사용자의 DTO 리스트를 포함한 {@link PageResponse}
     */
    PageResponse getFriendRequests(Long id, Pageable pageable);

    /**
     * 친구 요청을 보내기 위해 특정 사용자의 닉네임으로 사용자를 검색함
     *
     * @param nickname 친구요청을 보내고픈 사용자의 닉네임
     * @return 해당 닉네임을 가진 사용자의 정보를 담은 {@link FriendInfoDto} DTO
     */
    FriendInfoDto searchUserByNickname(String nickname);
}
