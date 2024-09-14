package main

import (
	"fmt"
	"io/ioutil"
	"kyc/database"
	"net/http"

	"github.com/gin-gonic/gin"
)

var db = database.InitDB()

func getNameScreen(c *gin.Context) {
	c.HTML(http.StatusOK, "name.html", nil)
}

func getEmailScreen(c *gin.Context) {
	c.HTML(http.StatusOK, "email.html", nil)
}

func postEmail(c *gin.Context) {
	email := c.PostForm("email")
	var customer database.Customer
	db.Last(&customer)
	customer.Email = email
	db.Save(&customer)
	c.Redirect(http.StatusFound, "/email")
}

func getPhoneScreen(c *gin.Context) {
	c.HTML(http.StatusOK, "phone.html", nil)
}

func postPhone(c *gin.Context) {
	phone := c.PostForm("phone")
	var customer database.Customer
	db.Last(&customer)
	customer.Phone = phone
	db.Save(&customer)
	c.Redirect(http.StatusFound, "/phone")
}

func getAddressScreen(c *gin.Context) {
	c.HTML(http.StatusOK, "address.html", nil)
}

func postAddress(c *gin.Context) {
	address := c.PostForm("address")
	var customer database.Customer
	db.Last(&customer)
	customer.Address = address
	db.Save(&customer)
	c.Redirect(http.StatusOK, "/address")
}

func getAddressProofScreen(c *gin.Context) {
	c.HTML(http.StatusOK, "addressProof.html", nil)
}

func postAddressProof(c *gin.Context) {
	address := c.PostForm("addressProof")
	var customer database.Customer
	db.Last(&customer)
	customer.AddressProofType = address
	// Parse file from the form
	file, err := c.FormFile("document")
	if err != nil {
		c.String(http.StatusBadRequest, fmt.Sprintf("File upload error: %s", err.Error()))
		return
	}

	// Open the file
	f, err := file.Open()
	if err != nil {
		c.String(http.StatusInternalServerError, fmt.Sprintf("File open error: %s", err.Error()))
		return
	}
	defer f.Close()

	// Read the file as a byte array
	fileBytes, err := ioutil.ReadAll(f)
	if err != nil {
		c.String(http.StatusInternalServerError, fmt.Sprintf("File read error: %s", err.Error()))
		return
	}
	customer.AddressProofDoc = fileBytes
	db.Save(&customer)
	c.Redirect(http.StatusOK, "/addressProof")
}
func postName(c *gin.Context) {
	name := c.PostForm("name")
	customer := database.Customer{Name: name}
	db.Create(&customer)
	c.Redirect(http.StatusFound, "/name")
}

func getIdentityProofScreen(c *gin.Context) {
	c.HTML(http.StatusOK, "identityProof.html", nil)
}

func postIdentityProof(c *gin.Context) {
	identityProof := c.PostForm("identityProof")
	var customer database.Customer
	db.Last(&customer)
	customer.IdentityProofType = identityProof
	// Parse file from the form
	file, err := c.FormFile("document")
	if err != nil {
		c.String(http.StatusBadRequest, fmt.Sprintf("File upload error: %s", err.Error()))
		return
	}

	// Open the file
	f, err := file.Open()
	if err != nil {
		c.String(http.StatusInternalServerError, fmt.Sprintf("File open error: %s", err.Error()))
		return
	}
	defer f.Close()

	// Read the file as a byte array
	fileBytes, err := ioutil.ReadAll(f)
	if err != nil {
		c.String(http.StatusInternalServerError, fmt.Sprintf("File read error: %s", err.Error()))
		return
	}
	customer.IdentityProofDoc = fileBytes
	db.Save(&customer)
	c.Redirect(http.StatusOK, "/identityProof")
}

func getBankDetailsScreen(c *gin.Context) {
	c.HTML(http.StatusOK, "bankDetails.html", nil)
}

func postBankDetails(c *gin.Context) {
	accountNumber := c.PostForm("AccountNumber")
	ifsc := c.PostForm("IFSC")
	branch := c.PostForm("Branch")
	name := c.PostForm("BankName")
	var customer database.Customer
	db.Last(&customer)
	customer.BankAccountNumber = accountNumber
	customer.BankIFSC = ifsc
	customer.BankBranch = branch
	customer.BankName = name
	db.Save(&customer)
	c.Redirect(http.StatusOK, "/bankDetails")
}

func main() {
	router := gin.Default()

	// Routes for each input screen
	router.GET("/name", getNameScreen)
	router.POST("/name", postName)

	router.GET("/email", getEmailScreen)
	router.POST("/email", postEmail)

	router.GET("/phone", getPhoneScreen)
	router.POST("/phone", postPhone)

	router.GET("/address", getAddressScreen)
	router.POST("/address", postAddress)

	router.GET("/addressproof", getAddressProofScreen)
	router.POST("/addressproof", postAddressProof)

	router.GET("/identityproof", getIdentityProofScreen)
	router.POST("/identityproof", postIdentityProof)

	router.GET("/bankdetails", getBankDetailsScreen)
	router.POST("/bankdetails", postBankDetails)

	// Start the server
	router.Run(":8080")
}
