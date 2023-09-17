package com.yeoboge.server.domain.dto.recommend;

import com.yeoboge.server.domain.dto.boardGame.BoardGameThumbnailDto;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 개인 사용자에게 추천 및 보드게임 목록을 전달할 VO
 *
 * @param shelves 각 카테고리 별로 {@link BoardGameThumbnailDto} 목록이 연결된 {@link Map}
 */
public record RecommendForSingleResponse(
        Queue<String> keys,
        Map<String, List<BoardGameThumbnailDto>> shelves,
        Map<String, String> descriptions
) {
}
