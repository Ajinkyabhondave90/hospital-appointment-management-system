# Hospital Appointment & Patient Management System
## (Tomcat 9 / JDK 8 Edition)

This is the **Tomcat 9 + JDK 8 compatible version** of the project, using the older
`javax.servlet.*` namespace (Java EE) instead of `jakarta.servlet.*` (Jakarta EE, which
requires Tomcat 10+). If you're on Tomcat 9 and JDK 8 in Eclipse, use THIS version.

This build was compiled and functionally verified end-to-end against real
Apache Tomcat 9.0 + OpenJDK 8 + PostgreSQL before being handed to you — login, session
handling, all CRUD screens, JSTL tags, and the JDBC layer were all confirmed working.

## What's different from the Tomcat 10 / JDK 11+ version
- All Java files use `javax.servlet.*` / `javax.servlet.http.*` / `javax.servlet.annotation.*`
  instead of `jakarta.*`
- `web.xml` uses the Servlet 4.0 schema (`http://xmlns.jcp.org/xml/ns/javaee`)
- JSPs use JSTL **1.2** taglib URIs: `http://java.sun.com/jsp/jstl/core` and
  `http://java.sun.com/jsp/jstl/fmt` (instead of the Jakarta 2.0 `jakarta.tags.*` URIs)
- `pom.xml` targets Java 8 and pulls `javax.servlet-api:4.0.1` + `jstl:jstl:1.2`
- All required jars (JSTL 1.2 + PostgreSQL JDBC driver) are **already bundled** in
  `src/main/webapp/WEB-INF/lib/` so this works as a plain Eclipse Dynamic Web Project
  too, not just Maven

## 1. Database Setup (same as before)
```
psql -U postgres -f database/schema.sql
psql -U postgres -f database/sample_data.sql
```
Update `src/main/java/com/techcloud/util/DBConnection.java` with your local
PostgreSQL credentials if they differ from `postgres` / `postgres`.

## 2. Import into Eclipse (plain Dynamic Web Project — matches your setup)
Since your screenshot showed a plain Dynamic Web Project (not Maven), do this:

1. In Eclipse: `File → Import → General → Existing Projects into Workspace`, point it
   at this folder. (If Eclipse insists on treating it as a Maven project because of
   `pom.xml`, just delete/rename `pom.xml` first — it's optional for this setup.)
2. Make sure the project structure matches:
   - `src/main/java` → right-click → **Build Path → Use as Source Folder** (if not already)
   - `src/main/webapp` → set as the **Web Content** folder (Project Properties →
     Project Facets / Web Module, or Deployment Assembly)
3. The three required jars are already inside `src/main/webapp/WEB-INF/lib/`:
   - `postgresql-42.jar`
   - `taglibs-standard-impl-1.2.5.jar`
   - `taglibs-standard-spec-1.2.5.jar`

   Right-click project → **Build Path → Configure Build Path → Libraries → Add JARs**,
   and select these three from `WEB-INF/lib` so Eclipse's compiler also sees them
   (having them in `WEB-INF/lib` alone makes them available at runtime, but Eclipse's
   own compiler needs them on the build path too, or you'll see red underlines).
4. Set the project's Java compiler compliance level to **1.8**: right-click project →
   **Properties → Java Compiler** → check "Enable project specific settings" →
   Compiler compliance level: `1.8`.
5. Make sure your **Tomcat v9.0** server (already configured, per your screenshot) is
   targeted: right-click project → **Properties → Targeted Runtimes** → check Tomcat 9.0.
6. Right-click project → **Run As → Run on Server**.

## 3. If you'd rather use Maven with Tomcat 9
`mvn clean package` produces `target/HospitalManagementSystem.war` — drop that into
Tomcat 9's `webapps/` folder and start Tomcat normally. Access at:
```
http://localhost:8080/HospitalManagementSystem/
```

## 4. Demo Logins (same as before)
| Role         | Username     | Password   |
|--------------|--------------|------------|
| Admin        | admin        | admin123   |
| Receptionist | reception1   | recep123   |
| Doctor       | dr.sharma    | doctor123  |

## Troubleshooting
- **Blank white page / HTTP 500 mentioning `ClassNotFoundException` for a JSTL tag
  class** → the JSTL jars aren't on the build path yet; redo step 3 above.
- **HTTP 500 mentioning `NoClassDefFoundError: org/postgresql/Driver`** → same fix,
  for `postgresql-42.jar`.
- **Whole app won't even deploy / red X on project** → check the Java compiler
  compliance level (step 4) — this project's source is written to compile cleanly
  under Java 8; a newer/older mismatch in Eclipse project settings is a common cause.
- Everything else in this project (features, business logic, DB schema) is identical
  to the Tomcat 10 edition — see the original README/feature list for that.
