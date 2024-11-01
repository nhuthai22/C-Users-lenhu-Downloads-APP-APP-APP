var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];

	$scope.load_all = function() {
		$http.get(`/rest/accounts`).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		})
	}
	$scope.reset = function() {
		$scope.form = {};
		$scope.key = null;
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	};

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			Swal.fire({
				icon: "success",
				title: "Thêm Thành công",
				text: "Người dùng mới đã được thêm vào danh sách",
			});
		}).catch(error => {
			Swal.fire({
				icon: "error",
				title: "Lỗi",
				text: "Thêm mới người dùng gặp trục trặc",
			});
			console.log("cc");
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[item] = item
			$scope.reset();
			Swal.fire({
				icon: "success",
				title: "Cập nhật người dùng Thành công",
				text: "Người dùng đã được cập nhật",
			});
		}).catch(error => {
			Swal.fire({
				icon: "error",
				title: "Lỗi",
				text: "Cập nhật người dùng gặp trục trặc",
			});
		})
	};


	$scope.delete = function(item) {
		$http.delete(`/rest/accounts/${item.username}`).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items.splice(index, 1);
			$scope.reset();
			Swal.fire({
				icon: "success",
				title: "Xóa người dùng Thành công",
				text: "Người dùng đã được xóa khỏi danh sách",
			});
		}).catch(error => {
			Swal.fire({
				icon: "error",
				title: "Lỗi",
				text: "Xóa người dùng gặp trục trặc",
			});
		});
	}

/*	$scope.searchAccounts = function(searchText) {
		$http.get(`/rest/accounts/search?username=${searchText}`).then(resp => {
			$scope.items = resp.data;
			console.log("Error", error);
		}).catch(error => {
			console.log("Error", error);
		});
	};*/



	$scope.load_all();
	$scope.reset();
});
