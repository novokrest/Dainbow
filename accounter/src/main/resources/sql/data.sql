INSERT INTO account (
  username,
  password
) VALUES (
  'user1',
  'one'
);

INSERT INTO account_role (
  username,
  role
) VALUES (
  'user1',
  'USER'
);

INSERT INTO oauth_client_details (
  client_id,
  resource_ids,
  client_secret,
  scope,
  authorized_grant_types,
  web_server_redirect_uri,
  authorities,
  access_token_validity,
  refresh_token_validity,
  additional_information,
  autoapprove
) VALUES (
  'client-ui',
  '',
  'Ya_O9/:ln0h}`h1',
  'read',
  'password,authorization_code,refresh_token',
  '',
  '',
  NULL,
  NULL,
  '{}',
  ''
);