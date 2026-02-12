FluxPay
Spring Boot ile geliştirilmiş, güvenlik ve performans odaklı mikro-finans transfer motoru.

Temel Özellikler
BCrypt Security: Kullanıcı PIN kodları veritabanında hashing algoritması ile şifrelenmiş olarak saklanır.

Transactional Logic: Para transferleri @Transactional ile atomik olarak yönetilir, veri kaybı yaşanmaz.

Global Exception Handling: Tüm hatalar merkezi bir yapıdan (@ControllerAdvice) yönetilir.

Real-time Dashboard: Vanilla JS ve Fetch API ile geliştirilmiş dinamik işlem paneli.

Teknolojiler
Java 17 & Spring Boot 3

Spring Data JPA & MySQL

Spring Security (Crypto)

Maven