import http from "k6/http";
import { Httpx } from "https://jslib.k6.io/httpx/0.1.0/index.js";

export const options = {
  stages: [
    { duration: "10s", target: 10 },
    { duration: "10s", target: 50 },
    { duration: "10s", target: 50 },
    { duration: "10s", target: 0 },
  ],
};

const EMAIL = "user@gmail.com";
const PASSWORD = "senha";

const session = new Httpx({
  baseURL: "http://172.17.0.3:8080/api",
  headers: { "Content-Type": "application/json" },
  timeout: 5000,
});

export function authToken() {
  session.post(
    `/user/create`,
    JSON.stringify({
      email: EMAIL,
      senha: PASSWORD,
    })
  );
}
export default function () {
  const url = "http://172.17.0.3:8080/api/user/login";

  const payload = JSON.stringify({
    email: "user@gmail.com",
    senha: "senha",
  });

  const headers = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  http.post(url, payload, headers);
}
