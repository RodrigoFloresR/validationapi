package com.example.validationapi.controller;
import com.example.validationapi.model.CardDetails;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
public class ProcessPaymentController {

    // Endpoint for the index menu, localhost:8080/
    // Displays the form for the cardDetails object with the respective fields
    // Sends a POST request to the endpoint "/validate"
    @GetMapping("/")
    public String index (Model model) {
      CardDetails request = new CardDetails();
      model.addAttribute("cardDetails", request);
        return "index";
    }

    // The endpoint is reached after the form is submitted
    // Redirects the user to a "success" view if the credit card details are valid
    // Redirects the user to the index if the credit card details are invalid with the error messages
    @PostMapping("/validate")
    public String cardValidation (@ModelAttribute("cardDetails") @Valid CardDetails cardDetails,
                                  BindingResult bindingResult, Model model) {
        String cc = cardDetails.getCcNumber();
        String cvv = String.valueOf(cardDetails.getCvv());
        validateCcExpiration(cardDetails.getCcExpiration(), bindingResult);

        if (bindingResult.hasErrors()){
            return "index";
        }
        if ((cc.startsWith("34") || cc.startsWith("37")) && cvv.length() != 4) {
            bindingResult.addError(cvvError());
            return "index";
        }

        if (!cc.startsWith("37") && !cc.startsWith("34") && cvv.length() != 3){
            bindingResult.addError(cvvError());
            return "index";
        }
        else
        return "success";
    }


    // Validates the expiry date
    private void validateCcExpiration(String ccExpiration, BindingResult bindingResult) {
        if (ccExpiration == null || ccExpiration.isEmpty()) {
            bindingResult.addError(expError());
            return;
        }

        // Validates the format "yyyy/MM"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        try {
            YearMonth inputYearMonth = YearMonth.parse(ccExpiration, formatter);
            YearMonth currentYearMonth = YearMonth.now();

            if (inputYearMonth.isBefore(currentYearMonth) || inputYearMonth.equals(currentYearMonth)) {
                bindingResult.addError(expError());
            }
        } catch (DateTimeParseException e) {
            bindingResult.addError(expError());
        }
    }

    // Generates a custom error message if the CVV details are invalid...
    // ...depending on the type of credit card (if the CVV is too short or too long for the credit card type)
    private FieldError cvvError() {
        return new FieldError("cardDetails", "cvv", "The cvv is invalid");
    }

    // Generates a custom error message if the expiry date details are invalid
    private FieldError expError() {
        return new FieldError("cardDetails", "ccExpiration", "Invalid expiry date");
    }
}

