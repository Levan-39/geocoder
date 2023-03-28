package ru.kubsu.geocoder.client;
/**
 * Copyright 2023
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kubsu.geocoder.dto.NominatimPlace;

import java.util.List;
import java.util.Optional;

/**
 * @author levan
 */
@FeignClient(value = "nominatim", url = "https://nominatim.openstreetmap.org")
public interface NominatimClient {
  String JSON_FORMAT = "json";

  @RequestMapping(method = RequestMethod.GET, value = "/search", produces = "application/json")
  List<NominatimPlace> search(@RequestParam(value = "q") String query,
                              @RequestParam(value = "format") String format);

  @RequestMapping(method = RequestMethod.GET, value = "/reverse", produces = "application/json")
  NominatimPlace reverse(@RequestParam(value = "lat") Double lat,
                         @RequestParam(value = "lon") Double lon,
                         @RequestParam(value = "format") String format);


  /**
   * Поиск объекта на карте по адресной строке в свободном параметре
   * В случаее нескольких подходящий объектов будет возвращен самый релевантный
   *
   * @param query строка поиска
   * @return объект адреса
   */
  default Optional<NominatimPlace> search(final String query) {
    try {
      return Optional.of(search(query, JSON_FORMAT).get(0));
    } catch (Exception ex) {
      return Optional.empty();
    }
  }
}
