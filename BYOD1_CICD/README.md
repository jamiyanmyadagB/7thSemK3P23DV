# BYOD CI/CD Pipeline Setup Guide

This guide will walk you through setting up a complete CI/CD pipeline using Jenkins for a Java Maven application.

## Prerequisites

- Java JDK 11 or higher installed
- Apache Maven installed
- Jenkins installed and running
- Git installed
- A GitHub account

---

## Step 1: Create GitHub Repository

1. Log in to your GitHub account
2. Click the **+** icon in the top-right corner
3. Select **New repository**
4. Enter a repository name (e.g., `byod-cicd`)
5. Choose **Public** or **Private**
6. Click **Create repository**

---

## Step 2: Initialize Git and Push Code

Open your terminal/command prompt in the project directory:

```bash
# Initialize git repository
git init

# Add all files
git add .

# Commit changes
git commit -m "Initial commit: Add Jenkins pipeline and Maven project"

# Add remote repository
git remote add origin https://github.com/jamiyanmyadagB/BYOD1_CICD.git

# Push to GitHub
git branch -M main
git push -u origin main
```

**Important:** Replace `YOUR_USERNAME` and `YOUR_REPO` with your actual GitHub username and repository name.

---

## Step 3: Update Jenkinsfile

1. Open the `Jenkinsfile` in your project
2. Update line 7 with your actual GitHub repository URL:

```groovy
git url: 'https://github.com/jamiyanmyadagB/BYOD1_CICD.git', branch: 'main'
```

3. Save the file
4. Commit and push the changes:

```bash
git add Jenkinsfile
git commit -m "Update Jenkinsfile with correct repository URL"
git push
```

---

## Step 4: Configure Jenkins

### 4.1 Install Required Plugins

1. Open Jenkins in your browser (usually `http://localhost:8080`)
2. Go to **Manage Jenkins** → **Manage Plugins**
3. Go to **Available** tab
4. Search for and install:
   - **Git Plugin**
   - **Pipeline Maven Plugin** (optional but recommended)
5. Click **Install without restart** or **Download now and install after restart**

### 4.2 Create New Pipeline Job

1. Click **New Item** on the Jenkins dashboard
2. Enter a name (e.g., `BYOD-CICD-Pipeline`)
3. Select **Pipeline**
4. Click **OK**

### 4.3 Configure Pipeline

1. Scroll down to **Pipeline** section
2. Select **Pipeline script from SCM**
3. Select **Git** from the SCM dropdown
4. Enter your **Repository URL**: `https://github.com/YOUR_USERNAME/YOUR_REPO.git`
5. For **Branches to build**, enter: `*/main`
6. For **Script Path**, enter: `Jenkinsfile`
7. Click **Save**

---

## Step 5: Run the Pipeline

1. On the job page, click **Build Now**
2. Click on the build number to view the progress
3. You should see the following stages execute:
   - **Checkout**: Clones the repository
   - **Build**: Compiles the Java application with Maven
   - **Test**: Runs JUnit tests
   - **Archive**: Saves the JAR file if build succeeds

---

## Step 6: Verify the Build

1. After the build completes, check the **Console Output**
2. You should see:
   ```
   Building the application...
   [INFO] BUILD SUCCESS
   Running tests...
   [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
   Archiving artifacts...
   ```
3. The JAR file will be archived in Jenkins under **Build Artifacts**

---

## Step 7: Test Locally (Optional)

To test the application locally before pushing:

```bash
# Clean and build
mvn clean package

# Run tests
mvn test

# Run the application
java -jar target/byod-cicd-1.0-SNAPSHOT.jar
```

---

## Project Structure

```
BYOD-CICD/
├── Jenkinsfile              # Jenkins pipeline configuration
├── pom.xml                  # Maven project configuration
├── README.md                # This file
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               └── App.java       # Main application class
    └── test/
        └── java/
            └── com/
                └── example/
                    └── AppTest.java   # JUnit test class
```

---

## Troubleshooting

### Build fails with "Maven not found"
- Ensure Maven is installed on the Jenkins server
- Configure Maven in Jenkins: **Manage Jenkins** → **Global Tool Configuration**

### Git authentication fails
- Use SSH URL instead of HTTPS: `git@github.com:YOUR_USERNAME/YOUR_REPO.git`
- Or configure GitHub credentials in Jenkins: **Manage Jenkins** → **Credentials**

### Tests fail
- Check the test output in Jenkins console
- Ensure Java 11+ is installed and configured

---

## Pipeline Stages Explained

1. **Checkout**: Pulls the latest code from GitHub
2. **Build**: Compiles the Java code and creates a JAR file using Maven
3. **Test**: Executes all JUnit tests to verify code quality
4. **Archive**: Saves the built JAR file as an artifact for deployment

---

## Next Steps

- Add a **Deploy** stage to deploy to a server
- Configure **webhooks** for automatic builds on push
- Add **code quality** tools (SonarQube, Checkstyle)
- Set up **notifications** (email, Slack) for build status
