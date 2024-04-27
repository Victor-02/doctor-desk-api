import { Counter, Rate } from "k6/metrics";
import http from "k6/http";
import { fail } from "k6";
import { Httpx } from "https://jslib.k6.io/httpx/0.1.0/index.js";
//import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

//export function handleSummary(data) {
//  return {
//    "summary.html": htmlReport(data),
//  };
//}

export const options = {
  stages: [
    { duration: "30s", target: 10 },
    { duration: "30s", target: 50 },
    { duration: "30s", target: 50 },
    { duration: "30s", target: 0 },
  ]
 }

const EMAIL = "user@gmail.com";
const PASSWORD = "senha";

const session = new Httpx({
  baseURL: "http://localhost:8080/api",
  headers: { "Content-Type": "application/json" },
  timeout: 5000,
});

export function authToken() {
  const loginResp = session.post(
    `/user/login`,
    JSON.stringify({
      email: EMAIL,
      senha: PASSWORD,
    })
  );
  console.log(loginResp.status);
  if (loginResp.status !== 200) {
    fail("login failed");
  }

  return loginResp.json("token");
}

export default function testSuite() {
  const getPacientes = http.get("http://localhost:8080/api/pacientes", {
    headers: {
      Authorization: `Bearer ${authToken()}`,
      "Content-Type": "application/json",
    },
  });
    console.log(getPacientes.status);
}
