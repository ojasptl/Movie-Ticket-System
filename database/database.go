package database

import (
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type Customer struct {
	gorm.Model
	Name              string
	Email             string
	Phone             string
	Address           string
	AddressProofType  string
	AddressProofDoc   []byte
	IdentityProofType string
	IdentityProofDoc  []byte
	BankAccountNumber string
	BankIFSC          string
	BankBranch        string
	BankName          string
}

func InitDB() *gorm.DB {
	dsn := ""
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	db.AutoMigrate(&Customer{})
	return db
}
