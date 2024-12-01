# Use Node.js as the base image
FROM node:18

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install application dependencies
RUN npm install --legacy-peer-deps

# Copy the rest of the application source code
COPY . .

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Expose the port the app runs on
EXPOSE 4200

# Start the Angular development server
CMD ["ng", "serve", "--host", "0.0.0.0"]
