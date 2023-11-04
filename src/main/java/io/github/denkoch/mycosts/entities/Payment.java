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
    private Category category;
    private PaymentType paymentType;
}
