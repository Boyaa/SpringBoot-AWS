package com.boya.aws.springboot.service.posts;

import com.boya.aws.springboot.domain.posts.Posts;
import com.boya.aws.springboot.domain.posts.PostsRepository;
import com.boya.aws.springboot.web.dto.PostsListResponseDto;
import com.boya.aws.springboot.web.dto.PostsResponseDto;
import com.boya.aws.springboot.web.dto.PostsSaveRequestDto;
import com.boya.aws.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save (PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update (Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다요 id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
    @Transactional(readOnly = true)
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다요 id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도 개선됨 (등록, 수정, 삭제 기능이 없을 때 사용)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        postsRepository.delete(posts); // JPA에서 delete 메소드를 지원한다
        //deleteById 메소드를 이용하면 id로 삭제할 수도 있다
        // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제한다
    }


}
