package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * DayVisitsStatisticDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-27T02:02:07.747436500+03:00[Europe/Moscow]")
public class DayVisitsStatisticDto {

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  @JsonProperty("visitsCount")
  private Integer visitsCount;

  public DayVisitsStatisticDto date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", required = false)
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public DayVisitsStatisticDto visitsCount(Integer visitsCount) {
    this.visitsCount = visitsCount;
    return this;
  }

  /**
   * Get visitsCount
   * @return visitsCount
  */
  
  @Schema(name = "visitsCount", required = false)
  public Integer getVisitsCount() {
    return visitsCount;
  }

  public void setVisitsCount(Integer visitsCount) {
    this.visitsCount = visitsCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DayVisitsStatisticDto dayVisitsStatisticDto = (DayVisitsStatisticDto) o;
    return Objects.equals(this.date, dayVisitsStatisticDto.date) &&
        Objects.equals(this.visitsCount, dayVisitsStatisticDto.visitsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, visitsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DayVisitsStatisticDto {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    visitsCount: ").append(toIndentedString(visitsCount)).append("\n");
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

