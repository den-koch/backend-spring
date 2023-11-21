package io.github.denkoch.mycosts.entities;

import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @NonNull
    private Integer id;
    private LocalDateTime dateTime;
    private Integer moneyAmount;
    @NonNull
    private Integer userId;
    private Integer categoryId;
    private PaymentType paymentType;
}
