Link deployment: https://advpro-eshop-veronica-kylie.koyeb.app/

### Refleksi 1
Prinsip clean code yang telah saya terapkan adalah sebagai berikut.
- **Meaningful names**: saya telah memberikan nama-nama yang jelas dan self-explanatory pada variabel-variabel yang saya gunakan
- **Function**: saya membuat function yang berukuran kecil dan hanya melakukan satu hal, serta nama function menjelaskan apa yang dikerjakan. Selain itu, function yang saya buat juga tidak menimbulkan efek samping yang tidak sesuai dengan fungsi utamanya.
- **Comments**: code yang dibuat pada tutorial ini masih cukup simpel sehingga alurnya dapat dibaca hanya dengan melihat nama variabel dan function yang dipanggil. Maka, tidak diperlukan comment untuk code yang self-explanatory.
- **Layout and Formatting**: saya membuat code yang terlihat rapih dengan memanfaatkan baris kosong antara tiap function dan indentasi yang seragam.

Prinsip secure coding yang telah saya terapkan adalah penggunaan method POST saat membuat produk baru.

Kesalahan yang masih saya temukan dalam code saya adalah sebagai berikut.
- Semua orang masih bisa menghapus dan menambahkan data sehingga perlu ditambahkan sistem autentikasi dan autorisasi
- Function edit di service layer mengembalikan object produk yangg telah diedit padahal tidak digunakan. Menurut saya, lebih baik jika function edit diganti sehingga tidak mengembalikan nilai apapun.
- Function delete belum ada respon untuk menanggapi jika productId yang dimasukan tidak ditemukan. Maka dari itu, perlu ditambahkan error handling untuk bagian ini agar tersampaikan pesan bahwa ID tidak ditemukan.

### Refleksi 2
1. Jawaban:
    - Setelah menuliskan unit testing saya merasa bahwa code yang saya buat lebih terpercaya dan dapat dijamin kebenarannya.
    - Menurut saya, tidak ada jawaban pasti untuk banyaknya unit test yang harus dibuat. Jumlah unit test ini pasti akan sangat bervariasi tergantung seberapa kompleks program kita dan berapa banyak macam-macam skenario yang dapat dilakukan oleh pengguna.
    - Beberapa hal yang pasti harus di testing adalah positive flow dan negative flow (misal: id tidak ditemukan). Kita juga harus mencari-cari edge case yang mungkin ada dan membuat testing untuk hal tersebut.
    - Code coverage juga dapat digunakan sebagai patokan dalam menentukan apakah test yang dibuat sudah cukup. Banyak yang setuju bahwa 80% code coverage merupakan parameter yang menunjukkan test sudah cukup.
    - 100% code coverage tidak menunjukkan bahwa program bebas dari bug dan error. Hal ini karena banyak sekali kemungkinan input yang diluar ekspektasi atau karena pembuatan test yang hanya seadanya saja.


2. Membuat Java class baru dengan setup dan instance variables yang sama membuat kodenya menjadi redundant sehingga hal ini mengurangi cleanliness dari code tersebut. Functional test suite yang baru akan mengulang proses exercise yang dilakukan pada testing create product sehingga code menjadi berulang-ulang. Menurut saya, akan lebih baik jika testing untuk verifikasi jumlah item dalam list dibuat dalam satu Java class yang sama sehingga tidak perlu mengulang proses setup yang sama lagi. Proses create product yang terulang juga dapat dimasukkan ke sebuah method sendiri agar dapat dipanggil oleh kedua functional test.

### Refleksi 3 (Modul 2)
1. Ada dua macam code quality issues yang saya perbaiki, yaitu sebagai berikut.
   - Token-Permissions: masalah ini muncul pada file ci.yml yang tidak diatur permissionnya menjadi read-only. Hal ini diperlukan untuk membatasi wewenang dari orang-orang yang tidak berkepentingan. Maka dari itu, saya menambahkan ```permissions: read-all``` pada file ci.yml sesuai rekomendasi pada remediation yang tertera.
   - Pinned-Dependencies: masalah ini saya perbaiki pada sonarcloud.yml dengan mengubah versi sonarcloud yang ingin digunakan menjadi versi hash-nya. Hal ini saya lakukan sesuai dengan remediation yang tertulis di Github.
   - Tidak sesuai camel case naming convention: Saya mengubah nama method di ProductController dari Home menjadi home agar sesuai dengan camel case naming convention.
   
2. CI atau Continuous Integration berarti adanya verifikasi untuk keseluruhan codebase ketika ada perubahan atau tambahan kode baru. Umumnya, hal ini dilakukan dengan sebuah build script yang dijalankan setiap kali ada perubahan pada codebase. Hal ini telah dilakukan melalui script ci.yml yang selalu menjalankan unit test setiap kali ada push baru ke Github. Selain itu, ada tools SonarCloud dan OSSF Scorecard yang mengecek mengenai potensi ancaman keamanan dan masalah-masalah yang ada pada codebase. Kedua tools ini menjalani analisisnya setiap kali ada perubahan pada codebase dimana hal ini sesuai dengan prinsip CI. 
Sedangkan, CD atau Continuous Deployment berarti adanya deploy secara otomatis setiap kali ada build terbaru dari codebase. Hal ini telah diwujudkan dengan Koyeb yang melakukan deployment secara otomatis ketika ada push baru ke branch main. Maka dari itu, saya dapat menarik kesimpulan bahwa proyek saya telah menerapkan prinsip CI/CD.