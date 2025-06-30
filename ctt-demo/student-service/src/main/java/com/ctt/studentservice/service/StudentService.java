package com.ctt.studentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ctt.studentservice.client.AddressFeignClient;
import com.ctt.studentservice.entity.Student;
import com.ctt.studentservice.repository.StudentRepository;
import com.ctt.studentservice.request.CreateStudentRequest;
import com.ctt.studentservice.response.AddressResponse;
import com.ctt.studentservice.response.StudentResponse;

import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressFeignClient addressFeignClient;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());

        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
        // studentResponse.setAddressResponse(getAddressById(student.getAddressId())); // from web client
        studentResponse.setAddressResponse(addressFeignClient.getAddressById(student.getAddressId())); // with feign client

        return studentResponse;
    }

    public StudentResponse getStudentById(long userId) {
        Student student = studentRepository.findById(userId).get();

        StudentResponse studentResponse = new StudentResponse(student);
        // studentResponse.setAddressResponse(getAddressById(student.getAddressId())); // from web client
        studentResponse.setAddressResponse(addressFeignClient.getAddressById(student.getAddressId())); // with feign client

        return studentResponse;

    }

    // example use of feign client
    public AddressResponse getAddressById (long addressId) {
        Mono<AddressResponse> addressResponse = webClient.get().uri("/" + addressId)
            .retrieve().bodyToMono(AddressResponse.class);

        return addressResponse.block();
    }
}
