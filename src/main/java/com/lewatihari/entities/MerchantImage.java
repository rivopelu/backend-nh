package com.lewatihari.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merchant_image")
public class MerchantImage {
    @Id
    private String id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "merchants_id")
    private Merchant merchant;
}
