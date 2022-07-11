import Bar from "../components/Bar";
import React from 'react';
import GithubButton from "react-github-login-button";
import GoogleButton from "react-google-button";


function LoginPage() {
    return (
        <React.Fragment>
            <Bar/>
            <a href='http://localhost:8080/api/auth' rel='noopener noreferrer' style = {{textDecoration: "none"}}>
                <GoogleButton style={{
                    margin: "auto",
                    marginTop: "200px",
                    display: "flex",
                    width: "20%"
                }}/>
            </a>
            <a href='http://localhost:8080/api/auth_github' rel='noopener noreferrer' style = {{textDecoration: "none"}}>
                <GithubButton style={{
                    margin: "auto",
                    marginTop: "20px",
                    display: "flex",
                    width: "20%"
                }}/>
            </a>

        </React.Fragment>
    );
}

export default LoginPage