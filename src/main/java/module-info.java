module com.example.laba_5_sem_2_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laba_5_sem_2_javafx to javafx.fxml;
    exports com.example.laba_5_sem_2_javafx;
}