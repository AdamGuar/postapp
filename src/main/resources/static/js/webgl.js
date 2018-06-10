var camera, scene, renderer;
var geometry, material, mesh;

function drawCanvas() {
  var canvas = document.getElementById('glCanvas');


  camera = new THREE.PerspectiveCamera(70, 600 / 400, 1, 1000);
  camera.position.z = 600;


  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xe0e0e0);

  // geometry = new THREE.BoxGeometry(200, 200, 200);
  // material = new THREE.MeshNormalMaterial();
  //
  // mesh = new THREE.Mesh(geometry, material);
  // scene.add(mesh);

  renderer = new THREE.WebGLRenderer({
    antialias: true
  });

  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);
  // canvas.addEventListener( 'touchmove', onDocumentTouchMove, false );


}

// function onDocumentTouchMove( event ) {
//   if ( event.touches.length === 1 ) {
//     event.preventDefault();
//     mouseX = event.touches[ 0 ].pageX - windowHalfX;
//     targetRotation = targetRotationOnMouseDown + ( mouseX - mouseXOnMouseDown ) * 0.05;
//   }
// }
