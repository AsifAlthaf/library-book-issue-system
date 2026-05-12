# 📚 Library Book Issue & Return Service

A professional RESTful backend service for managing library operations with complete CRUD functionality, business rule enforcement, and **interactive Swagger UI documentation**.

## 🎯 Project Overview

This system automates the college library book issuing process, managing books, registering members, issuing books, and handling returns while enforcing business rules.

## ✨ Features

- ✅ **Complete Book Management** - Add, view, search books by title/author
- ✅ **Member Management** - Register members, view details and issued books
- ✅ **Issue & Return Operations** - Issue books to members and handle returns
- ✅ **Business Rule Enforcement** - Max 3 books per member, availability checks
- ✅ **Comprehensive Validation** - Input validation with detailed error messages
- ✅ **Professional Error Handling** - Global exception handling with proper HTTP status codes
- ✅ **H2 In-Memory Database** - Pre-loaded with sample data for testing
- ✅ **Transaction Management** - ACID compliance for all operations
- ✅ **RESTful API Design** - Standard HTTP methods and status codes
- ✅ **Swagger UI Integration** - Interactive API documentation 🎯 **NEW!**
- ✅ **OpenAPI 3.0 Specification** - Industry-standard API documentation

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+ (or use included Maven Wrapper)

### Running the Application

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

The application will start on **http://localhost:8080**

### Access Points
- **Swagger UI**: http://localhost:8080/swagger-ui.html 🎯 **Try this first!**
- **API Base URL**: http://localhost:8080/api
- **H2 Console**: http://localhost:8080/h2-console
- **OpenAPI Docs**: http://localhost:8080/api-docs

## 📖 Interactive API Documentation

### Swagger UI - Your Best Friend! 🎯

Open **http://localhost:8080/swagger-ui.html** to:
- ✅ **Explore all 13 API endpoints** interactively
- ✅ **Test APIs directly** from your browser
- ✅ **See request/response examples** in real-time
- ✅ **No Postman needed** - everything in one place
- ✅ **Professional interface** - perfect for demos

**See [SWAGGER_GUIDE.md](SWAGGER_GUIDE.md) for detailed instructions**

## 📋 API Endpoints

### Book Management (6 endpoints)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/books` | Add new book |
| GET | `/api/books` | Get all books |
| GET | `/api/books/available` | Get available books |
| GET | `/api/books/{bookId}` | Get book by ID |
| GET | `/api/books/search/title?title={term}` | Search by title |
| GET | `/api/books/search/author?author={term}` | Search by author |

### Member Management (5 endpoints)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/members` | Register member |
| GET | `/api/members` | Get all members |
| GET | `/api/members/{memberId}` | Get member by ID |
| GET | `/api/members/{memberId}/issues` | Get all issues |
| GET | `/api/members/{memberId}/active-issues` | Get active issues |

### Issue & Return (2 endpoints)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/issues/issue` | Issue a book |
| PUT | `/api/issues/return/{issueId}` | Return a book |

## 🎮 Quick Test with Swagger

1. **Start the application**
2. **Open Swagger UI**: http://localhost:8080/swagger-ui.html
3. **Click on "Book Management"**
4. **Click on "GET /api/books"**
5. **Click "Try it out"**
6. **Click "Execute"**
7. **See the response!** ✨

## 📝 Sample Requests

### Using Swagger UI (Recommended)
Just open http://localhost:8080/swagger-ui.html and click "Try it out" on any endpoint!

### Using cURL

#### Get All Books
```bash
curl http://localhost:8080/api/books
```

#### Add a New Book
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","author":"Robert C. Martin"}'
```

#### Issue a Book
```bash
curl -X POST http://localhost:8080/api/issues/issue \
  -H "Content-Type: application/json" \
  -d '{"bookId":1,"memberId":1}'
```

## 🎲 Sample Test Data

The application automatically initializes with:

**Books:**
- Clean Code by Robert C. Martin
- Effective Java by Joshua Bloch
- Design Patterns by Gang of Four
- Head First Java by Kathy Sierra
- Spring in Action by Craig Walls

**Members:**
- John Doe (john.doe@example.com)
- Jane Smith (jane.smith@example.com)
- Alice Johnson (alice.johnson@example.com)

## 📖 Business Rules

1. **Book Availability**: A book can only be issued if it's available
2. **Maximum Issues**: A member can have a maximum of 3 active book issues
3. **Unique Email**: Each member must have a unique email address
4. **Active Issues**: Only active issues can be returned

## 🛠️ Technology Stack

- **Java 17** - Programming Language
- **Spring Boot 4.0.6** - Application Framework
- **Spring Data JPA** - Data Access Layer
- **H2 Database** - In-Memory Database
- **Lombok** - Boilerplate Code Reduction
- **Bean Validation** - Input Validation
- **Swagger/OpenAPI 3** - API Documentation 🎯 **NEW!**
- **SpringDoc OpenAPI** - Swagger Integration
- **Maven** - Build Tool

## 📚 Documentation

- **[SWAGGER_GUIDE.md](SWAGGER_GUIDE.md)** - Interactive API documentation guide 🎯 **START HERE!**
- **[API_DOCUMENTATION.md](API_DOCUMENTATION.md)** - Complete API reference
- **[QUICK_START.md](QUICK_START.md)** - Step-by-step getting started
- **[PROBLEM_STATEMENT.md](PROBLEM_STATEMENT.md)** - Requirements and specifications
- **[ARCHITECTURE.md](ARCHITECTURE.md)** - System architecture
- **[FEATURES.md](FEATURES.md)** - Complete feature list
- **[ALL_CLASSES.md](ALL_CLASSES.md)** - Complete class inventory
- **[POSTMAN_COLLECTION.json](POSTMAN_COLLECTION.json)** - Postman collection

## 🧪 Testing

### Option 1: Swagger UI (Easiest!)
1. Open http://localhost:8080/swagger-ui.html
2. Click "Try it out" on any endpoint
3. Execute and see results

### Option 2: Postman
1. Import `POSTMAN_COLLECTION.json`
2. Run the collection

### Option 3: Automated Script
```bash
cd library-book-issue-system
test-apis.bat
```

## 🎯 What's New - Swagger UI

### ✨ Interactive Documentation
- **Visual Interface**: Beautiful, professional API documentation
- **Try It Out**: Test APIs directly from browser
- **Real-time Testing**: See responses instantly
- **No Setup Required**: Works out of the box

### 📊 Features
- **13 Endpoints**: All APIs documented
- **3 API Groups**: Organized by functionality
- **Request Examples**: Pre-filled sample data
- **Response Codes**: All possible responses documented
- **Schema Documentation**: Complete data models

### 🎮 How to Use
1. Start application
2. Open http://localhost:8080/swagger-ui.html
3. Explore and test APIs
4. No external tools needed!

## 🏗️ Architecture

```
Controller Layer → Service Layer → Repository Layer → Database
```

- **Controllers**: Handle HTTP requests (3 controllers)
- **Services**: Business logic (3 services)
- **Repositories**: Data access (3 repositories)
- **Entities**: Domain models (3 entities)
- **DTOs**: Data transfer objects (4 DTOs)
- **Exception Handling**: Global error handling

## 🎯 Hackathon Deliverables

✅ Working REST APIs (13 endpoints)  
✅ Validations and error handling  
✅ H2 in-memory database  
✅ Sample test data pre-loaded  
✅ Postman collection for testing  
✅ Complete API documentation  
✅ Professional code structure  
✅ Transaction management  
✅ Business rule enforcement  
✅ **Swagger UI integration** 🎯 **NEW!**  
✅ **Interactive API testing** 🎯 **NEW!**  

## 📄 License

This project is created for educational purposes.

## 👨‍💻 Support

For questions or issues:
1. Check **[SWAGGER_GUIDE.md](SWAGGER_GUIDE.md)** for Swagger UI help
2. Check **[QUICK_START.md](QUICK_START.md)** for setup instructions
3. Review **[API_DOCUMENTATION.md](API_DOCUMENTATION.md)** for API details
4. Use Swagger UI for interactive testing

---

**Built with ❤️ using Spring Boot and Swagger UI**

**🎯 Try Swagger UI now**: http://localhost:8080/swagger-ui.html
