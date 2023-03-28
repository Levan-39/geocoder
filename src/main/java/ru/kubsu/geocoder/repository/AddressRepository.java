package ru.kubsu.geocoder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kubsu.geocoder.model.Address;

import java.util.Optional;

/**
 *
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
  Optional<Address> findByAddress(final String address);
  Optional<Address> findByLatitudeAndLongitude(final Double latitude, final Double longitude);
}
