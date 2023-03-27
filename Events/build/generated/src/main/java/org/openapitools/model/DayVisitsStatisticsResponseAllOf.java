package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.DayVisitsStatisticDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * DayVisitsStatisticsResponseAllOf
 */

@JsonTypeName("DayVisitsStatisticsResponse_allOf")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-27T02:02:07.747436500+03:00[Europe/Moscow]")
public class DayVisitsStatisticsResponseAllOf {

  @JsonProperty("result")
  @Valid
  private List<DayVisitsStatisticDto> result = null;

  public DayVisitsStatisticsResponseAllOf result(List<DayVisitsStatisticDto> result) {
    this.result = result;
    return this;
  }

  public DayVisitsStatisticsResponseAllOf addResultItem(DayVisitsStatisticDto resultItem) {
    if (this.result == null) {
      this.result = new ArrayList<>();
    }
    this.result.add(resultItem);
    return this;
  }

  /**
   * Get result
   * @return result
  */
  @Valid 
  @Schema(name = "result", required = false)
  public List<DayVisitsStatisticDto> getResult() {
    return result;
  }

  public void setResult(List<DayVisitsStatisticDto> result) {
    this.result = result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DayVisitsStatisticsResponseAllOf dayVisitsStatisticsResponseAllOf = (DayVisitsStatisticsResponseAllOf) o;
    return Objects.equals(this.result, dayVisitsStatisticsResponseAllOf.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayVisitsStatisticsResponseAllOf {\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

