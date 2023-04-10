/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import org.openapitools.model.SubscriptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-27T02:02:07.747436500+03:00[Europe/Moscow]")
@Validated
@Tag(name = "subscriptions", description = "the subscriptions API")
public interface SubscriptionsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /subscriptions/{subscriptionId}/add : Add subscription by id
     *
     * @param subscriptionId  (required)
     * @param dateFrom  (required)
     * @param dateTo  (required)
     * @return OK (status code 200)
     *         or Client error (status code 400)
     *         or Server error (status code 500)
     */
    @Operation(
        operationId = "addSubscription",
        summary = "Add subscription by id",
        tags = { "subscriptions" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Client error"),
            @ApiResponse(responseCode = "500", description = "Server error")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/subscriptions/{subscriptionId}/add",
        produces = { "application/json" }
    )
    default ResponseEntity<SubscriptionResponse> addSubscription(
        @Parameter(name = "subscriptionId", description = "", required = true) @PathVariable("subscriptionId") Integer subscriptionId,
        @NotNull @Parameter(name = "dateFrom", description = "", required = true) @Valid @RequestParam(value = "dateFrom", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
        @NotNull @Parameter(name = "dateTo", description = "", required = true) @Valid @RequestParam(value = "dateTo", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /subscriptions/{subscriptionId} : Get subscription info by id
     *
     * @param subscriptionId  (required)
     * @return Subscription info by id (status code 200)
     *         or Client error (status code 400)
     *         or Server error (status code 500)
     */
    @Operation(
        operationId = "getSubscriptionInfo",
        summary = "Get subscription info by id",
        tags = { "subscriptions" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Subscription info by id", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Client error"),
            @ApiResponse(responseCode = "500", description = "Server error")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/subscriptions/{subscriptionId}",
        produces = { "application/json" }
    )
    default ResponseEntity<SubscriptionResponse> getSubscriptionInfo(
        @Parameter(name = "subscriptionId", description = "", required = true) @PathVariable("subscriptionId") Integer subscriptionId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /subscriptions/{subscriptionId}/update : Update subscription by id
     *
     * @param subscriptionId  (required)
     * @param duration  (required)
     * @return OK (status code 200)
     *         or Client error (status code 400)
     *         or Server error (status code 500)
     */
    @Operation(
        operationId = "updateSubscription",
        summary = "Update subscription by id",
        tags = { "subscriptions" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SubscriptionResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Client error"),
            @ApiResponse(responseCode = "500", description = "Server error")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/subscriptions/{subscriptionId}/update",
        produces = { "application/json" }
    )
    default ResponseEntity<SubscriptionResponse> updateSubscription(
        @Parameter(name = "subscriptionId", description = "", required = true) @PathVariable("subscriptionId") Integer subscriptionId,
        @NotNull @Parameter(name = "duration", description = "", required = true) @Valid @RequestParam(value = "duration", required = true) Long duration
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}