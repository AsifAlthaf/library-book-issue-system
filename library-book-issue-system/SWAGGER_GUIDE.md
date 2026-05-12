# 📘 Swagger UI Guide - Library Book Issue System

## 🎯 Overview

Swagger UI has been integrated into the Library Book Issue System to provide **interactive API documentation**. You can now explore, test, and understand all API endpoints through a beautiful web interface.

---

## 🚀 Accessing Swagger UI

### Start the Application
```bash
cd library-book-issue-system
mvnw.cmd spring-boot:run
```

### Open Swagger UI
Once the application is running, open your browser and navigate to:

**Swagger UI**: http://localhost:8080/swagger-ui.html

**OpenAPI JSON**: http://localhost:8080/api-docs

---

## 📋 What You'll See

### 1. **API Information**
- **Title**: Library Book Issue & Return Service API
- **Version**: 1.0.0
- **Description**: Complete API documentation
- **Server**: http://localhost:8080

### 2. **API Groups** (Tags)
The APIs are organized into three logical groups:

#### 📚 Book Management
- POST `/api/books` - Add a new book
- GET `/api/books` - Get all books
- GET `/api/books/available` - Get available books
- GET `/api/books/{bookId}` - Get book by ID
- GET `/api/books/search/title` - Search by title
- GET `/api/books/search/author` - Search by author

#### 👥 Member Management
- POST `/api/members` - Register a new member
- GET `/api/members` - Get all members
- GET `/api/members/{memberId}` - Get member by ID
- GET `/api/members/{memberId}/issues` - Get all issues
- GET `/api/members/{memberId}/active-issues` - Get active issues

#### 📖 Issue & Return Operations
- POST `/api/issues/issue` - Issue a book
- PUT `/api/issues/return/{issueId}` - Return a book

---

## 🎮 How to Use Swagger UI

### Testing an Endpoint

#### Example 1: Get All Books
1. Click on **"Book Management"** to expand
2. Click on **GET /api/books**
3. Click **"Try it out"** button
4. Click **"Execute"** button
5. View the response below

#### Example 2: Add a New Book
1. Click on **"Book Management"** to expand
2. Click on **POST /api/books**
3. Click **"Try it out"** button
4. Edit the request body:
```json
{
  "title": "Java Concurrency in Practice",
  "author": "Brian Goetz"
}
```
5. Click **"Execute"** button
6. View the response (201 Created)

#### Example 3: Issue a Book
1. Click on **"Issue & Return Operations"** to expand
2. Click on **POST /api/issues/issue**
3. Click **"Try it out"** button
4. Edit the request body:
```json
{
  "bookId": 1,
  "memberId": 1
}
```
5. Click **"Execute"** button
6. View the response

---

## 📊 Understanding the Interface

### Request Section
- **Parameters**: Path parameters, query parameters
- **Request Body**: JSON payload for POST/PUT requests
- **Example Values**: Pre-filled examples you can use

### Response Section
- **Response Code**: HTTP status code (200, 201, 400, 404, etc.)
- **Response Body**: JSON response from the server
- **Response Headers**: HTTP headers
- **Curl Command**: Copy-paste ready curl command

### Schemas Section
At the bottom of the page, you'll find:
- **ApiResponse**: Standard response wrapper
- **Book**: Book entity structure
- **BookRequest**: Request for adding books
- **IssueBookRequest**: Request for issuing books
- **IssueRecord**: Issue record structure
- **Member**: Member entity structure
- **MemberRequest**: Request for registering members

---

## 🎨 Features

### 1. **Interactive Testing**
- Test all endpoints directly from the browser
- No need for Postman or curl
- Instant feedback

### 2. **Request Examples**
- Pre-filled example values
- Easy to modify and test
- Realistic sample data

### 3. **Response Codes**
- See all possible response codes
- Understand success and error scenarios
- Clear descriptions

### 4. **Schema Documentation**
- View all data models
- Understand field types
- See validation rules

### 5. **Try It Out**
- Execute real API calls
- See actual responses
- Test business rules

---

## 📝 Sample Workflows

### Workflow 1: Complete Book Issue Flow

#### Step 1: View Available Books
1. GET `/api/books/available`
2. Execute
3. Note a book ID (e.g., 1)

#### Step 2: View Members
1. GET `/api/members`
2. Execute
3. Note a member ID (e.g., 1)

#### Step 3: Issue the Book
1. POST `/api/issues/issue`
2. Request body:
```json
{
  "bookId": 1,
  "memberId": 1
}
```
3. Execute
4. Note the issue ID from response

#### Step 4: Verify Book Status
1. GET `/api/books/1`
2. Execute
3. See `availability: false`

#### Step 5: Return the Book
1. PUT `/api/issues/return/{issueId}`
2. Use the issue ID from Step 3
3. Execute
4. See return date in response

---

### Workflow 2: Test Business Rules

#### Test 1: Maximum Book Limit
1. Issue 3 books to member 1
2. Try to issue a 4th book
3. See error: "Member has reached the maximum limit"

#### Test 2: Book Availability
1. Issue book 1 to member 1
2. Try to issue book 1 to member 2
3. See error: "Book is not available for issue"

#### Test 3: Email Uniqueness
1. Register member with email "test@example.com"
2. Try to register another member with same email
3. See error: "Member with email already exists"

---

## 🔍 Advanced Features

### 1. **Filtering Operations**
- Use the search box to filter endpoints
- Type "book" to see only book-related APIs
- Type "member" to see only member-related APIs

### 2. **Sorting**
- Operations are sorted by HTTP method
- Tags are sorted alphabetically
- Easy to navigate

### 3. **Curl Commands**
- Each request shows the equivalent curl command
- Copy and use in terminal
- Great for automation

### 4. **Response Schemas**
- Click on "Schema" tab to see structure
- Click on "Example Value" to see sample
- Understand data format

---

## 🎯 Configuration

The Swagger configuration is located in:
```
src/main/java/com/example/library_book_issue_system/config/OpenApiConfig.java
```

### Current Settings
```properties
# Swagger/OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
```

### Customization Options
You can customize:
- API title and description
- Server URLs
- Contact information
- License information
- Tags and grouping

---

## 📱 Screenshots Guide

### Main Page
- Shows all API groups
- Expandable sections
- Clean interface

### Endpoint Details
- HTTP method and path
- Description
- Parameters
- Request body
- Response codes

### Try It Out
- Editable request
- Execute button
- Live response
- Curl command

---

## 🚀 Benefits

### For Developers
- ✅ Quick API testing
- ✅ No external tools needed
- ✅ Instant feedback
- ✅ Easy debugging

### For Testers
- ✅ Interactive testing
- ✅ All endpoints in one place
- ✅ Test scenarios easily
- ✅ Validate responses

### For Documentation
- ✅ Always up-to-date
- ✅ Self-documenting
- ✅ Professional appearance
- ✅ Easy to share

---

## 🔗 Quick Links

| Resource | URL |
|----------|-----|
| **Swagger UI** | http://localhost:8080/swagger-ui.html |
| **OpenAPI JSON** | http://localhost:8080/api-docs |
| **H2 Console** | http://localhost:8080/h2-console |
| **Application** | http://localhost:8080 |

---

## 💡 Tips & Tricks

### Tip 1: Use Example Values
- Click "Example Value" to auto-fill request body
- Modify as needed
- Quick testing

### Tip 2: Check Response Codes
- Expand each endpoint
- See all possible responses
- Understand error scenarios

### Tip 3: Copy Curl Commands
- After executing a request
- Scroll down to "Curl" section
- Copy for automation

### Tip 4: Test Error Cases
- Try invalid data
- Test business rules
- See error messages

### Tip 5: Explore Schemas
- Scroll to bottom
- Click on schema names
- Understand data structures

---

## 🎓 Learning Path

### Beginner
1. Open Swagger UI
2. Explore the interface
3. Try GET endpoints
4. View responses

### Intermediate
1. Test POST endpoints
2. Modify request bodies
3. Test different scenarios
4. Understand error responses

### Advanced
1. Test business rules
2. Create complex workflows
3. Use curl commands
4. Integrate with automation

---

## 📚 Additional Resources

### Documentation
- **API_DOCUMENTATION.md** - Complete API reference
- **QUICK_START.md** - Getting started guide
- **README.md** - Project overview

### Testing
- **POSTMAN_COLLECTION.json** - Postman collection
- **test-apis.bat** - Automated test script

---

## ✅ Verification

To verify Swagger is working:

1. **Start the application**
```bash
mvnw.cmd spring-boot:run
```

2. **Open Swagger UI**
```
http://localhost:8080/swagger-ui.html
```

3. **You should see**:
   - ✅ API title and description
   - ✅ Three API groups (Book, Member, Issue)
   - ✅ 13 endpoints total
   - ✅ Interactive "Try it out" buttons

---

## 🎉 Summary

Swagger UI provides:
- ✅ **Interactive API documentation**
- ✅ **Live API testing**
- ✅ **No external tools needed**
- ✅ **Professional interface**
- ✅ **Always up-to-date**
- ✅ **Easy to use**
- ✅ **Great for demos**

**Access it now**: http://localhost:8080/swagger-ui.html

---

**Version**: 1.0.0  
**Last Updated**: May 12, 2026  
**Status**: ✅ Active
