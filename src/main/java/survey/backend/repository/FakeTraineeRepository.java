//package survey.backend.repository;
//
//import survey.backend.dto.TraineeDto;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.random.RandomGenerator;
//
//public class FakeTraineeRepository {
//
//    private Set<TraineeDto> trainees;
//
//    public FakeTraineeRepository(){
//        this.createFakeRepository();
//    }
//    private static final RandomGenerator RANDOM_ID_GENERATOR = RandomGenerator.getDefault();
//
//    public Set<TraineeDto> getTrainees(){
//        return this.trainees;
//    }
//
//    public Optional<TraineeDto> findById(int id){
//        return this.trainees.stream()
//                .filter(trainee -> trainee.getId() == id)
//                .findFirst();
//    }
//
//    public boolean delete(TraineeDto stagiaire){
//        return this.trainees.remove(stagiaire);
//    }
//
//    public TraineeDto add(TraineeDto traineeDto){
//        traineeDto.setId(this.nextId());
//        this.trainees.add(traineeDto);
//        return traineeDto;
//    }
//
//    private Integer nextId(){
//
//        final int[] nextId = {1};
//
//        this.trainees.forEach( t -> {
//            if(t.getId() > nextId[0]){
//                nextId[0] = t.getId();
//            }
//        });
//        return Integer.valueOf(nextId[0]+1);
//
//
////        TraineeDto[] trainees = (TraineeDto[]) this.trainees.stream().toArray();
////        int nextId = 1;
////        for(TraineeDto trainee : trainees){
////            if(trainee.getId() > nextId){
////                nextId = trainee.getId();
////            }
////        }
////        return Integer.valueOf(nextId + 1);
//    }
//
//
//
//      private void createFakeRepository(){
//        this.trainees = Set.of(
//                TraineeDto.builder()
//                        .id(1)
//                        .lastName("Doe")
//                        .firstName("John")
//                        .birthDate(LocalDate.of(1961, 10, 23))
//                        .build(),
//                TraineeDto.builder()
//                        .id(2)
//                        .lastName("Dupont")
//                        .firstName("Robert")
//                        .birthDate(LocalDate.of(1985, 3, 22))
//                        .build(),
//                TraineeDto.builder()
//                        .id(26)
//                        .lastName("Martin")
//                        .firstName("Claire")
//                        .birthDate(LocalDate.of(1988, 5, 26))
//                        .build(),
//                TraineeDto.builder()
//                        .id(8)
//                        .lastName("Doe")
//                        .firstName("Jane")
//                        .birthDate(LocalDate.of(1945, 7, 1))
//                        .email("janedoe@gmail.com")
//                        .build()
//        ).stream().collect(Collectors.toSet());
//    }
//}
