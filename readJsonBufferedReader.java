        try {
            JSONObject data = new JSONObject();
            data.put("username", "");
            data.put("password", "");

            URL url = new URL("");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");

            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(data.toString().getBytes());
            Integer responseCode = httpConnection.getResponseCode();

            BufferedReader bufferedReader;

            if (responseCode > 199 && responseCode < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content.toString());
            String loudScreaming = json.get("token").toString();

            System.out.println(loudScreaming);

        } catch (Exception e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        
        
        
                User user = new User();
        user.setUsername("");
        user.setPassword("");
        Entity<User> entity = Entity.json(user);

//        Form form = new Form();
//        form
//                .param("username", "")
//                .param("password", "");
//        Entity<Form> entity = Entity.form(form);

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("");
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .post(entity);

        String out = response.readEntity(String.class);
        System.out.println(out);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(out);

        String token = json.get("token").toString();
        System.out.print(token);
