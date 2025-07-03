package com.ctt.studentservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    CommonService commonService;

    //@Autowired
    //WebClient webClient;

    //@Autowired
    //AddressFeignClient addressFeignClient;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());

        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;
    }

    public StudentResponse getStudentById(long userId) {
        Student student = studentRepository.findById(userId).get();

        StudentResponse studentResponse = new StudentResponse(student);
        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;

    }
}

// due to AOP, these functions are moved to the CommonService
// we call this in other functions
// need to set up the resilience4j in properties, "addressService" is defined in properties
//@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
//public AddressResponse getAddressById (long addressId) {
//    AddressResponse addressResponse = addressFeignClient.getAddressById(addressId);
//
//    return addressResponse;
//}
//
//public AddressResponse fallbackGetAddressById (long addressId, Throwable th) {
//    return new AddressResponse();
//}

// previous code that has the web client
//public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
//
//    Student student = new Student();
//    student.setFirstName(createStudentRequest.getFirstName());
//    student.setLastName(createStudentRequest.getLastName());
//    student.setEmail(createStudentRequest.getEmail());
//    student.setAddressId(createStudentRequest.getAddressId());
//
//    student = studentRepository.save(student);
//
//    StudentResponse studentResponse = new StudentResponse(student);
//    // studentResponse.setAddressResponse(getAddressById(student.getAddressId())); // from web client
//    studentResponse.setAddressResponse(addressFeignClient.getAddressById(student.getAddressId())); // with feign client
//
//    return studentResponse;
//}
//
//public StudentResponse getStudentById(long userId) {
//    Student student = studentRepository.findById(userId).get();
//
//    StudentResponse studentResponse = new StudentResponse(student);
//    // studentResponse.setAddressResponse(getAddressById(student.getAddressId())); // from web client
//    studentResponse.setAddressResponse(addressFeignClient.getAddressById(student.getAddressId())); // with feign client
//
//    return studentResponse;
//
//}
//
//// example use of webclient client
//public AddressResponse getAddressById (long addressId) {
//    Mono<AddressResponse> addressResponse = webClient.get().uri("/" + addressId)
//            .retrieve().bodyToMono(AddressResponse.class);
//
//    return addressResponse.block();
//}
