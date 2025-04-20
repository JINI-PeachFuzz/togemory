package org.jinju.wishlist.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jinju.member.entities.Member;
import org.jinju.wishlist.constants.WishType;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WishId {
    private Long seq;
    private WishType type;
    private Member member;
}
