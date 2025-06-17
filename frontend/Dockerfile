FROM node:22 AS builder
WORKDIR /app
COPY . .
RUN npm ci && npm run build --configuration=production

FROM nginx:stable-alpine
COPY --from=builder /app/dist/employee-ui/browser  /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 4200
