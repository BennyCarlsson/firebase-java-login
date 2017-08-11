function firebaseGmailLogin(){
// Initialize Firebase
  var config = {
    apiKey: "AIzaSyCZKQQT1l3tF7IW3JycMsYIYpSc2-b_u98",
    authDomain: "yesno-2dbf9.firebaseapp.com",
    databaseURL: "https://yesno-2dbf9.firebaseio.com",
    storageBucket: "yesno-2dbf9.appspot.com",
  };
  firebase.initializeApp(config);
    var provider = new firebase.auth.GoogleAuthProvider();

    firebase.auth().onAuthStateChanged(function(user) {
      if (user) {
        user.getIdToken(true).then(function(idToken){
            //if user logged in redirects and send idtoken to backend
            window.location.href=`/?idToken=${idToken}`;
        });


      } else {
        firebase.auth().signInWithRedirect(provider).then(function(result) {
                          console.log(result)
                        }).catch(function(error) {
                          console.log("error")
                        });
      }
    });
}
(() => firebaseGmailLogin())();