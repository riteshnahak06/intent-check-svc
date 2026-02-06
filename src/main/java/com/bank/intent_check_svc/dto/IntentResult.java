package com.bank.intent_check_svc.dto;

import com.bank.intent_check_svc.utils.ChatIntent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntentResult {

    // Detected user intent
    private ChatIntent intent;

    // Extracted numeric value (e.g. last 5 transactions, EMI amount)
    private Integer number;

    // Confidence score (useful later if you add ML)
    private Double confidence;

    // Whether confirmation is required (sensitive actions)
    private boolean confirmationRequired;

    // Optional follow-up context
    private String followUpKey;
}
