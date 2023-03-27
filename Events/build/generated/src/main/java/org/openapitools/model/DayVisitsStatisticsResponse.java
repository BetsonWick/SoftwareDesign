package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.CommonApiResponse;
import org.openapitools.model.DayVisitsStatisticDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * DayVisitsStatisticsResponse
 */


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-27T02:02:07.747436500+03:00[Europe/Moscow]")
public class DayVisitsStatisticsResponse extends CommonApiResponse {

  @JsonProperty("result")
  @Valid
  private List<DayVisitsStatisticDto> result = null;

  public DayVisitsStatisticsResponse result(List<DayVisitsStatisticDto> result) {
    this.result = result;
    return this;
  }

  public DayVisitsStatisticsResponse addResultItem(DayVisitsStatisticDto resultItem) {
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

  public DayVisitsStatisticsResponse className(String className) {
    super.setClassName(className);
    return this;
  }

  public DayVisitsStatisticsResponse host(String host) {
    super.setHost(host);
    return this;
  }

  public DayVisitsStatisticsResponse version(String version) {
    super.setVersion(version);
    return this;
  }

  public DayVisitsStatisticsResponse executingTime(String executingTime) {
    super.setExecutingTime(executingTime);
    return this;
  }

  public DayVisitsStatisticsResponse actions(String actions) {
    super.setActions(actions);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DayVisitsStatisticsResponse dayVisitsStatisticsResponse = (DayVisitsStatisticsResponse) o;
    return Objects.equals(this.result, dayVisitsStatisticsResponse.result) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayVisitsStatisticsResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

