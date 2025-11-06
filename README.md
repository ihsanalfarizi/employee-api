# 👥 Employee Management REST API

REST API sederhana untuk mengelola data karyawan di perusahaan Juke.

---

## ✨ Fitur

✅ Tambah, lihat, ubah, dan hapus data karyawan  
✅ Validasi data otomatis (email unik, gaji > 0)  
✅ Dokumentasi API interaktif dengan Swagger  
✅ Error handling dengan pesan yang jelas  
✅ Logging untuk tracking operasi  

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **PostgreSQL 16**
- **Maven**
- **Docker**
- **Swagger/OpenAPI**

---

## 📦 Yang Harus Disiapkan


### Untuk Developer:
- JDK 17+
- Maven 3.6+
- PostgreSQL 12+

---

## 🚀 Cara Menjalankan

### Menggunakan Docker (RECOMMENDED):

### Buka Docker Desktop Terlebih dahulu

```bash
# 1. Clone project
git clone https://github.com/ihsanalfarizi/employee-api.git
cd employee-api


# 2. Jalankan
./start-and-open.sh 

##atau mau cara manual 


# 3. Jalankan
docker-compose up -d --build

# 4. Tunggu sampai muncul: "Started EmployeeApiApplication"

#5. Untuk melihat isi tabel employees langsung dari PostgreSQL container:
docker exec -it employee-postgres \
  psql -U employee -d employeedb \
  -c "SELECT * FROM employees;"

# 6. Buka browser
http://localhost:8080/swagger-ui.html
```

## 📚 API Endpoints

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| GET | `/api/employees` | Lihat semua karyawan |
| GET | `/api/employees/{id}` | Lihat detail 1 karyawan |
| POST | `/api/employees` | Tambah karyawan baru |
| PUT | `/api/employees/{id}` | Update data karyawan |
| DELETE | `/api/employees/{id}` | Hapus karyawan |

## 💡 Contoh Penggunaan

### 1. Tambah Karyawan Baru

**Request:**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson",
    "email": "alice@juke.com",
    "position": "Senior Developer",
    "salary": 12000000
  }'
```

**Response:**
```json
{
  "id": 1,
  "name": "Alice Johnson",
  "email": "alice@juke.com",
  "position": "Senior Developer",
  "salary": 12000000.00,
  "createdAt": "2025-10-28T10:30:00"
}
```

---

### 2. Lihat Semua Karyawan

**Request:**
```bash
curl http://localhost:8080/api/employees
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Alice Johnson",
    "email": "alice@juke.com",
    "position": "Senior Developer",
    "salary": 12000000.00,
    "createdAt": "2025-10-28T10:30:00"
  }
]
```

---

### 3. Lihat Detail 1 Karyawan

**Request:**
```bash
curl http://localhost:8080/api/employees/1
```

---

### 4. Update Data Karyawan

**Request:**
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Smith",
    "email": "alice.smith@juke.com",
    "position": "Tech Lead",
    "salary": 15000000
  }'
```

---

### 5. Hapus Karyawan

**Request:**
```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

---

## 🧪 Cara Testing (Tanpa Coding!)

### Opsi 1: Swagger UI (PALING MUDAH)

1. Buka: `http://localhost:8080/swagger-ui.html`
2. Klik endpoint yang ingin dicoba
3. Klik **"Try it out"**
4. Isi data → Klik **"Execute"**
5. Lihat hasilnya



## 📝 Validasi Data

| Field | Aturan |
|-------|--------|
| **name** | Wajib, 2-100 karakter |
| **email** | Wajib, format email, harus unik |
| **position** | Wajib, 2-100 karakter |
| **salary** | Wajib, harus > 0 |

---

## ⚠️ Contoh Error

### Validasi Gagal (400)
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email should be valid",
    "salary": "Salary must be greater than 0"
  }
}
```

### Email Sudah Ada (409)
```json
{
  "status": 409,
  "message": "Email already exists: alice@juke.com"
}
```

### Data Tidak Ditemukan (404)
```json
{
  "status": 404,
  "message": "Employee not found with id: 99"
}
```

---



### Docker error
```bash
docker-compose down
docker-compose up --build
```

## 🛑 Stop Aplikasi

```bash
# Docker
docker-compose down

```
---


**Selesai! 🚀**
