# TaskFlow - Project Management System

## Project Description
TaskFlow is a streamlined task management application designed specifically for small to medium-sized development teams.The system provides an intuitive interface for organizing projects, tracking tasks through customizable workflows, and facilitating team collaboration—all without the complexity and overhead of larger enterprise solutions.

TaskFlow strikes a balance between simplicity and functionality, offering just enough features to effectively manage software development projects without unnecessary complications.

Key differentiators:

- Developer-centric: Designed by developers for developers, with workflows that match typical software development processes
- Lightweight: Fast performance with minimal resource requirements compared to enterprise alternatives

Whether you're managing sprint backlogs, tracking bugs, or coordinating feature development, TaskFlow provides the essential tools needed for effective project management without the complexity.
## Current Features

### User Management
- Email/password registration
- Basic profile management
- Session handling

### Project Organization
- Create/archive projects
- Invite team members
- Set project visibility

### Task Tracking
- Five-stage workflow (Backlog → Todo → In Progress → Review → Done)
- Priority levels (Low/Medium/High/Critical)
- Assignment system
- Due dates and time estimates

## Technical Implementation

### Backend Stack
- **Language**: Java 17
- **Framework**: Spring Boot 3.1.5
- **ORM**: Hibernate 6.x
- **Build Tool**: Maven

### Database Schema

![TaskFlow_db](images/TaskFlow_db.svg)

## Getting Started

### Prerequisites
* JDK 17+
* PostgreSQL
* Maven 3.8+

### Installation
1. Clone repository:
```bash
git clone https://github.com/yourusername/taskflow.git
```

2. Configure database in `src/main/resources/application.yml`
3. Build and run:
```bash
mvn spring-boot:run
```

## Database Structure

TaskFlow uses a relational database with the following main tables:

1. **users** - System users who can participate in projects
    - `id` (PK) - Unique ID
    - `username` - Unique login
    - `email` - Unique email address
    - `password_hash` - Secure password hash

2. **Projects** - Project containers for organizing tasks
    - `id` (PK) - Unique identifier
    - `name` - Project name
    - `password` - Optional password for access control
    - `creator_id` (FK → users.id) - Project creator reference

3. **project_members** - Junction table for user-project relationships
    - `id` (PK) - Unique identifier
    - `project_id` (FK → projects.id) - Project reference
    - `participant_id` (FK → users.id) - User reference
    - `role` (ENUM: 'owner', 'admin', 'member') - User role within project

4. **issues** - Task tracking information
    - `id` (PK) - Unique identifier
    - `project_id` (FK → projects.id) - Project reference
    - `title` - Task title
    - `description` - Detailed task description
    - `status` (ENUM: 'backlog', 'todo', 'in_progress', 'review', 'done')
    - `priority` (ENUM: 'low', 'medium', 'high', 'critical')
    - `assignee_id` (FK → users.id, nullable) - Assigned user
    - `reporter_id` (FK → users.id) - Task creator
    - `created_at` - Creation timestamp
    - `updated_at` - Last update timestamp
    - `due_date` - Task deadline
    - `estimated_time` - Time estimate in hours

## Roadmap

* Implement file attachments
* Add comments system
* Develop basic reporting
* Add time tracking functionality
* Create simple analytics dashboard

**Tips for future updates:**
1. Add `## API Documentation` section when endpoints are ready
2. Include screenshots in `## UI Overview` later
3. Expand `## Security` with OAuth2 details
4. Add `## Deployment` guide for production

## Contact
For contributions or questions:

Email: petcovnicola@gmail.com

Issues: https://github.com/users/Nickseen/projects/1