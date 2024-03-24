import { CredentialResponse, GoogleLogin } from '@react-oauth/google';
import { jwtDecode } from 'jwt-decode';
import { useAuth } from '../../auth/AuthProvider';

interface DecodedToken {
  aud: string; // Audience
  azp: string; // Authorized party
  email: string;
  email_verified: boolean;
  exp: number; // Expiration time
  family_name: string;
  given_name: string;
  iat: number; // Issued at
  iss: string; // Issuer
  jti: string; // JWT ID
  name: string;
  nbf: number; // Not before
  picture: string;
  sub: string; // Subject
}

export function LoginButton() {
  const { setUser } = useAuth();

  function handleSuccess(credentialResponse: CredentialResponse) {
    if (!credentialResponse.credential) {
      console.error('Credential is missing.');
      return;
    }

    const decodedToken = jwtDecode(
      credentialResponse.credential
    ) as DecodedToken;
    console.log(decodedToken);

    setUser({
      name: decodedToken.name,
      email: decodedToken.email,
    });
  }

  function handleError() {
    console.error('Login Failed:');
  }

  return (
    <GoogleLogin
      theme="filled_blue"
      onSuccess={handleSuccess}
      onError={handleError}
    />
  );
}
