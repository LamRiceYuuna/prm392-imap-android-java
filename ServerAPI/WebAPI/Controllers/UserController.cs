using Microsoft.AspNetCore.Mvc;
using WebAPI.DTOs.login;

namespace WebAPI.Controllers {
    [ApiController]
    [Route("/api/v1/user/")]
    public class UserController : ControllerBase {
        [HttpPost("login")]
        public IActionResult login(LoginRequest loginRequest) {

            return Ok(new {success=true, token="12345678910"});
        }
    }
}
