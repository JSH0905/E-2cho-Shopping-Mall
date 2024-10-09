package org.e2cho.e2cho_shopping_mall.domain.user;


import jakarta.persistence.*;
import lombok.*;
import org.e2cho.e2cho_shopping_mall.constant.SnsType;
import org.e2cho.e2cho_shopping_mall.dto.user.UserInfoUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User implements OAuth2User {

    // OAuth 2.0을 통한 회원 가입 시 기본으로 채워지는 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String snsId;

    @Column(nullable = false, unique = true)
    private SnsType snsType;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "profileImage")
    private String profileImage;

    // 회원 수정으로 채울 수 있는 필드
    @Column(name = "telephone")
    private String telephone;

    @Column(name = "home_address")
    private String address;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String authority;

    public static User of(String snsId, SnsType snsType, String name, String email, String profileImage) {
        return User.builder()
                .snsId(snsId)
                .snsType(snsType)
                .profileImage(profileImage)
                .name(name)
                .email(email)
                .authority("ROLE_USER")
                .build();
    }

    public void update(UserInfoUpdate.Request request){
        this.name = request.getName();
        this.address = request.getAddress();
        this.telephone = request.getTelephone();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public String getName() {
        return name;
    }


}
