/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.jpa.benchmark.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.benchmark.model.IPersonProjection;
import org.springframework.data.jpa.benchmark.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

/**
 * @author Christoph Strobl
 */
public interface PersonRepository extends ListCrudRepository<Person, Integer> {

	List<Person> findAllByFirstname(String firstname);

	List<IPersonProjection> findAllAndProjectToInterfaceByFirstname(String firstname);

	@Query("SELECT p FROM org.springframework.data.jpa.benchmark.model.Person p WHERE p.firstname = ?1")
	List<Person> findAllWithAnnotatedQueryByFirstname(String firstname);

	@Query("SELECT p FROM org.springframework.data.jpa.benchmark.model.Person p WHERE p.firstname = ?1")
	List<Person> findAllWithAnnotatedQueryByFirstname(String firstname, Sort sort);

	@Query(value = "SELECT * FROM person WHERE firstname = ?1", nativeQuery = true)
	List<Person> findAllWithNativeQueryByFirstname(String firstname);

	Long countByFirstname(String firstname);

	@Query("SELECT COUNT(*) FROM org.springframework.data.jpa.benchmark.model.Person p WHERE p.firstname = ?1")
	Long countWithAnnotatedQueryByFirstname(String firstname);
}
