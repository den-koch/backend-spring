package io.github.denkoch.mycosts.entities;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Category {
    @NonNull
    private Integer id;
    private String label;
    @NonNull
    private Integer userId;
}
