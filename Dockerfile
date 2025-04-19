FROM sonarsource/sonar-scanner-cli:latest

WORKDIR /usr/src/app

# Copy project code and sonar properties file
COPY . .

# Run SonarScanner
CMD ["sonar-scanner"]
