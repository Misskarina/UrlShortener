# UrlShortener
# UrlShortener

A lightweight Java-based URL shortening service that allows users to create short, shareable links for long URLs.

## 🚀 Features

- **Shorten URLs**: Convert long URLs into short, easy-to-share links.
- **Custom Aliases**: Optionally specify custom aliases for shortened URLs.
- **Persistent Storage**: Store mappings between original and shortened URLs.
- **Simple API**: Interact with the service programmatically.

## 🛠️ Technologies Used

- **Backend**: Java
- **Build Tool**: Maven
- **Persistence**: File / Database (Specify your storage)
- **API**: [Specify framework if any, e.g., Spring Boot]

## 📦 Installation

### Prerequisites

- Java 11 or higher
- Maven 3.6+

# UrlShortener

A simple Java-based URL shortening service that allows you to create short, shareable links for long URLs.

## API Endpoints

### POST `/api/shorten`

Shorten a given URL.

**Request Body:**

{
  "url": "https://www.example.com"
}

**Response:**

{
  "shortUrl": "http://localhost:8080/abc123"
}
