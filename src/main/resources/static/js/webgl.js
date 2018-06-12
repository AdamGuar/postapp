var camera, scene, renderer, controls;

var targetRotation = 0;
var targetRotationOnMouseDown = 0;
var mouseX = 0;
var mouseXOnMouseDown = 0;
var windowHalfX = 600 / 2;
var windowHalfY = 400 / 2;
var model;
function drawCanvas(m) {
  model = m;
  init();
  render();
  animate();
}

function init() {
  var canvas = document.getElementById('glCanvas');

  renderer = new THREE.WebGLRenderer({
    antialias: true
  });

  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);

  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xffffff);

  camera = new THREE.PerspectiveCamera(45, 600 / 400, 1, 10000);
  camera.position.z = 1;

  controls = new THREE.TrackballControls(camera);
  controls.rotateSpeed = 1.0;
  controls.zoomSpeed = 1.2;
  controls.panSpeed = 0.8;
  controls.noZoom = false;
  controls.noPan = false;
  controls.staticMoving = true;
  controls.dynamicDampingFactor = 0.3;
  controls.keys = [65, 83, 68];
  controls.addEventListener('change', render);

  scene.add(camera);


  var light = new THREE.AmbientLight(0xffffff);
  var pointLight = new THREE.PointLight( 0xffffff, 0.8 );
  camera.add( pointLight );
  scene.add(light);

  var max_temp = model.elements[0].nodes[0].value;
  var min_temp = model.elements[0].nodes[0].value;

  for (var i=0 ; i< model.elements.length ; i++) {
    const el = model.elements[i];
    for(var j=0 ; j<el.nodes.length ; j++) {
      if (el.nodes[j].value > max_temp) max_temp = el.nodes[j].value;
      if (el.nodes[j].value < min_temp) min_temp = el.nodes[j].value;
    }
  }

  console.log(max_temp);
  console.log(min_temp);


  for (var i=0 ; i< model.elements.length ; i++) {
    const el = model.elements[i];
    const width = el.nodes[1].x - el.nodes[0].x;
    const height = el.nodes[4].y - el.nodes[0].y;
    const depth = el.nodes[0].z - el.nodes[3].z;

    var geometry = new THREE.BoxGeometry(width, height, depth);
    const temp_sum = 0;
    for(var j=0 ; j<el.nodes.length ; j++) {
      color_sum += el.nodes[j].value;
    }
    const temp_avg = color_sum / el.nodes.length;
    const normalized = (temp_avg - min_temp) / (max_temp - min_temp);
    const r = normalized;
    const g = 1 - normalized;
    var c = new THREE.Color( r, g, 0 );
    var material = new THREE.MeshStandardMaterial({color: c});

    var mesh = new THREE.Mesh(geometry, material);
    mesh.position.set(el.nodes[0].x, el.nodes[0].y, el.nodes[0].z);
    scene.add(mesh);
  }


  render();
}

function render() {
  renderer.render(scene, camera);
}

function animate() {
  requestAnimationFrame(animate);
  controls.update();
}
