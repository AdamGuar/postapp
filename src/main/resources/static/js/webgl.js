var camera, scene, renderer;
var geometry, material, mesh;

function drawCanvas() {
  const canvas = document.querySelector("#glCanvas");



  camera = new THREE.PerspectiveCamera(70, 600 / 400, 1, 1000);
  camera.position.z = 600;
  camera.position.y = 150;


  scene = new THREE.Scene();
  scene.background = new THREE.Color( 0xf0f0f0 );

  geometry = new THREE.BoxGeometry(200, 200, 200);
  material = new THREE.MeshNormalMaterial();

  mesh = new THREE.Mesh(geometry, material);
  scene.add(mesh);

  renderer = new THREE.WebGLRenderer({
    antialias: true
  });
  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);
  canvas.addEventListener( 'touchmove', onDocumentTouchMove, false );


}

function onDocumentTouchMove( event ) {
  if ( event.touches.length === 1 ) {
    event.preventDefault();
    mouseX = event.touches[ 0 ].pageX - windowHalfX;
    targetRotation = targetRotationOnMouseDown + ( mouseX - mouseXOnMouseDown ) * 0.05;
  }
}
