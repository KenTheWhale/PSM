package com.team5.psm.models.request_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDraftBookingRequest {

    private String petName;

    private String spaName;

    private String serviceName;

    private float price;


}
